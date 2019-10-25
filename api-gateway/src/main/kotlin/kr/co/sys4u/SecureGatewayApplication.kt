package kr.co.sys4u

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@EnableDiscoveryClient
@SpringBootApplication
class SecureGatewayApplication
fun main(args: Array<String>) {
    runApplication<SecureGatewayApplication>(*args)
}
