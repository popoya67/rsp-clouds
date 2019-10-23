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
                route {
                    path("/customer/**")
                    uri("http://localhost:8081/")
                }
                route{
                    path("/topic**", "/topicCategory**", "/topics**")
                    uri("http://localhost:8082/")
                }
                route{
                    path("/auth/**")
                    uri("http://localhost:8083/")
                }
                route{
                    path("/menu/**")
                    uri("http://localhost:8084/")
                }
                route{
                    path("/authenticate**")
                    uri("http://localhost:8050/")
                }
            }
}