package kr.co.sys4u.config

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.filters
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Routes {
    @Bean
    fun myroutes(routeLocatorBuilder: RouteLocatorBuilder): RouteLocator =
            routeLocatorBuilder.routes {
                route{
                    path("/auth/**")
                    uri("http://localhost:7777/")
                }
                route{
                    path("/authenticate**")
                    uri("http://localhost:7777/")
                }
            }
}