package ru.lama.group.test.notes.client

import io.restassured.RestAssured.given
import io.restassured.response.Response
import ru.lama.group.test.notes.api.rq.CreateUserRq

class UserApiClient {

    fun addUser(request: CreateUserRq): Response {
        return given()
            .body(request)
            .post("/user")
    }
}