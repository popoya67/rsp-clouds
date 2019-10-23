package kr.co.sys4u.jwt

import io.jsonwebtoken.Jwts
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class JwtValidator(private val jwtKeyGenerator: JwtKeyGenerator) {
    private val LOGGER = LoggerFactory.getLogger(JwtValidator::class.java)

    fun isValidToken(jwt: String, secretKey: String): Boolean {
        try {
            Jwts.parser().setSigningKey(jwtKeyGenerator.generateKey(secretKey)).parseClaimsJws(jwt)
        } catch (e: Exception) {
            return false
        }

        return true
    }
}