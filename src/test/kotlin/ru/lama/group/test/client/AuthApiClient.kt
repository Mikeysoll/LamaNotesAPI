package ru.lama.group.test.client

import io.restassured.RestAssured.given
import io.restassured.response.Response
import ru.lama.group.test.notes.api.rq.AuthRq
import ru.lama.group.test.notes.api.rs.AuthRs
import ru.lama.group.test.base.baseRequestSpec
import ru.lama.group.test.context.Context

class AuthApiClient(
    private val context: Context
) {

    fun auth(): AuthRs {
        return given()
            .spec(baseRequestSpec())
            .queryParam("login", context.login)
            .queryParam("psw", context.psw)
            .body(AuthRq(context.login, context.psw))
            .post("/auth")
            .then()
            .extract()
            .`as`(AuthRs::class.java)
    }

    fun authError(): Response {
        return given()
            .spec(baseRequestSpec())
            .queryParam("login", context.login)
            .queryParam("psw", context.psw)
            .body(AuthRq(context.login, context.psw))
            .post("/auth")
            .then()
            .extract()
            .response()
    }
}