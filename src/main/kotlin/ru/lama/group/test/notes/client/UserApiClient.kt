package ru.lama.group.test.notes.client

import feign.Param
import feign.RequestLine
import feign.Response


interface UserApiClient {

    @RequestLine("POST /user")
    fun addUser(
        @Param("login")
        login: String,
        @Param("psw")
        psw: String,
        @Param("name")
        name: String
    ): Response
}
