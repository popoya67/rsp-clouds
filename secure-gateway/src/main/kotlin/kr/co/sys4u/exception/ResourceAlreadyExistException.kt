package kr.co.sys4u.exception

import java.lang.IllegalStateException

open class ResourceAlreadyExistException(message:String) : IllegalStateException(message) {
}
