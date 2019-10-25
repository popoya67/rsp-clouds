package kr.co.sys4u.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(value= HttpStatus.UNAUTHORIZED, reason="login required")
class LoginRequiredException(message: String) : RuntimeException(message)