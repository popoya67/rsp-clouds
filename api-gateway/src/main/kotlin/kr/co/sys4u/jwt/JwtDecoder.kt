package kr.co.sys4u.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.impl.DefaultJwtParser
import kr.co.sys4u.dto.AdminUser
import kr.co.sys4u.exception.JwtException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class JwtDecoder(private val jwtKeyGenerator: JwtKeyGenerator) {
    private val LOGGER = LoggerFactory.getLogger(JwtDecoder::class.java)

    fun getUser(jwt: String, secretKey: String): AdminUser {
        var claims: Jws<Claims>? = null
        try {
            claims = Jwts.parser().setSigningKey(jwtKeyGenerator.generateKey(secretKey)).parseClaimsJws(jwt)
        }catch( eje: ExpiredJwtException){
            throw eje
        }catch (e: Exception) {
            LOGGER.error(e.message, e)
            throw JwtException("decoding failed")
        }

        return ObjectMapper().convertValue(claims.body.get("USER"), AdminUser::class.java)
    }
}