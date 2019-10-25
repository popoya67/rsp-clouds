package kr.co.sys4u.config

import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.ExpiredJwtException
import kr.co.sys4u.dto.AdminUser
import kr.co.sys4u.dto.TokenSet
import kr.co.sys4u.exception.LoginRequiredException
import kr.co.sys4u.exception.LoginSessionExpiredException
import kr.co.sys4u.jwt.JwtDecoder
import kr.co.sys4u.jwt.JwtValidator
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.http.HttpMethod
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono
import java.util.*


@Configuration
@Order(3)
class AuthFilter(private val jwtValidator: JwtValidator
                 , private val jwtDecoder: JwtDecoder
                 , @Qualifier("authServiceClient") private val authServiceClient: WebClient.Builder): WebFilter{

    @Value("\${app.jwt.accesskey}")
    private lateinit var accessSecretKey: String

    @Value("\${app.open-api}")
    private lateinit var openApis: List<String>


    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {

        if(exchange.request.path.toString().startsWith("/actuator/")){
            return chain.filter(exchange)
        }

        if(exchange.request.method === HttpMethod.OPTIONS) {
            return chain.filter(exchange)
        }

        for (openApi in openApis) {
            if(exchange.request.path.toString().startsWith(openApi)){
                return chain.filter(exchange)
            }
        }

        if(exchange.request.headers["origin"] == null){
            return chain.filter(exchange)
        }

        val accessTokens = exchange.request.headers[Constants.ACCESS_TOKEN]
        if (accessTokens === null){
            throw LoginRequiredException("login required")
        }

        val accessToken = accessTokens!!.last()
        exchange.attributes[Constants.ACCESS_TOKEN] = accessToken

        //AT 만료시
        if (!jwtValidator.isValidToken(accessToken.toString(), accessSecretKey)) {
            try {
                jwtDecoder.getUser(accessToken, accessSecretKey)
            } catch (e: ExpiredJwtException) {
                //loginID로 RT가 유효한지 AuthService에게 요청
                var loginUser = ObjectMapper().convertValue(e.claims["USER"], AdminUser::class.java)

                return authServiceClient.build()
                        .post()
                        .uri("/auth/updateTokenIfLoginUser")
                        .body(BodyInserters.fromObject(loginUser.loginId))
                        .retrieve()
                        .bodyToMono(TokenSet::class.java).flatMap { tokenSet: TokenSet ->
                            exchange.attributes[Constants.ACCESS_TOKEN] = tokenSet.accessToken
                            exchange.response.headers.set(Constants.ACCESS_TOKEN, tokenSet.accessToken)

                            chain.filter(exchange)
                        }.onErrorResume {
                            it.printStackTrace()
                            throw LoginSessionExpiredException("login session expired")
                        }
            }
        }

        return chain.filter(exchange)
    }

}
