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
                    uri("lb://ec-customer/")
                }
                route{
                    path("/topic/**", "/topicCategory/**", "/topics/**")
                    uri("lb://ec-topic/")
                }
                route{
                    path("/auth/**")
                    uri("lb://ec-auth/")
                }
                route{
                    path("/menu/**")
                    uri("lb://ec-menu/")
                }
                route{
                    path("/authenticate/**")
                    uri("lb://ec-process-server/")
                }
//                route{
//                    path("/**")
//                    uri("http://localhost:7777/")
//                }
            }
}