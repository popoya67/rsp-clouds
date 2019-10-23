package kr.co.sys4u.dto

import org.springframework.data.mongodb.core.mapping.Document

@Document(collection="AdminUser")
data class AdminUser(
        val loginId : String
        , var password : String?
        , var name: String?
        , var grade: String?
        , var refreshToken: String?) {

    constructor() : this("", null, null, null, null)

}