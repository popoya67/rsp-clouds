package kr.co.sys4u

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class InnerGatewayApplication
fun main(args: Array<String>) {
    runApplication<InnerGatewayApplication>(*args)
}
