package kr.co.sys4u.exception

import java.lang.IllegalStateException

open class NoSuchResourceException(message:String) : IllegalStateException(message) {
}
