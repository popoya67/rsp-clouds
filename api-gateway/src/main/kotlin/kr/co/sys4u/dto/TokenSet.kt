package kr.co.sys4u.dto

data class TokenSet(var accessToken: String?, var refreshToken: String?) {
    constructor() : this(null, null)

    fun accessToken(token: String) : TokenSet {
        this.accessToken = token
        return this
    }

    fun refreshToken(token: String) : TokenSet {
        this.refreshToken = token
        return this
    }

}