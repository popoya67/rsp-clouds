package kr.co.sys4u.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig {

    @Value("\${app.service.topic-url}")
    lateinit var topicServiceUrl: String

    @Value("\${app.service.auth-url}")
    lateinit var authServiceUrl: String



    @Bean("authServiceClient")
    @LoadBalanced
    //@Scope(value="prototype", proxyMode= ScopedProxyMode.TARGET_CLASS)
    fun authServiceClient() : WebClient.Builder{
        return WebClient
                .builder()
                .baseUrl(authServiceUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
    }

    @Bean("topicServiceClient")
    @LoadBalanced
    //@Scope(value="prototype", proxyMode= ScopedProxyMode.TARGET_CLASS)
    fun topicServiceClient() : WebClient.Builder{
        return WebClient
                .builder()
                .baseUrl(topicServiceUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
    }
}