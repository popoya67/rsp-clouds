package kr.co.sys4u.config

import org.springframework.context.annotation.Bean
import org.springframework.core.annotation.Order
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsWebFilter
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource

@Component
class CorsFilter {
    @Bean
    @Order(1)
    fun corsWebFilter(): CorsWebFilter {
        val corsConfig = CorsConfiguration()
        corsConfig.addAllowedOrigin("*")
        corsConfig.maxAge = 8000L
        corsConfig.addAllowedMethod(HttpMethod.GET)
        corsConfig.addAllowedMethod(HttpMethod.POST)
        corsConfig.addAllowedMethod(HttpMethod.DELETE)
        corsConfig.addAllowedMethod(HttpMethod.PUT)
        corsConfig.addExposedHeader("X-MESSAGE")
        corsConfig.addAllowedHeader("X-Forward-Url")
        corsConfig.addExposedHeader("X-Forward-Url")

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", corsConfig)

        return CorsWebFilter(source)
    }
}