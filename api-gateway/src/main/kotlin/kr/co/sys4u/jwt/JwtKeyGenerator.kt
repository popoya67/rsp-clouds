package kr.co.sys4u.jwt

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.UnsupportedEncodingException

@Service
class JwtKeyGenerator {
    private val LOGGER = LoggerFactory.getLogger(JwtKeyGenerator::class.java)

    fun generateKey(secretKey: String): ByteArray? {
        var key: ByteArray? = null
        try {
            key = secretKey.toByteArray(charset("UTF-8"))
        } catch (e: UnsupportedEncodingException) {
            LOGGER.error("Making secret Key Error :: ", e)
        }

        return key
    }

}