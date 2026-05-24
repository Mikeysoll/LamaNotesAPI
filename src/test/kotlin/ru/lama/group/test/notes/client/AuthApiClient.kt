package ru.lama.group.test.notes.client

import io.restassured.RestAssured.given
import ru.lama.group.test.notes.api.rq.AuthRq
import ru.lama.group.test.notes.api.rs.AuthRs
import ru.lama.group.test.notes.base.baseRequestSpec
import ru.lama.group.test.notes.context.Context

class AuthApiClient(
    private val context: Context
) {

    fun auth(): AuthRs {
        return given()
            .spec(baseRequestSpec())
            .log().all()
            .queryParam("login", context.login)
            .queryParam("psw", context.psw)
            .body(AuthRq(context.login, context.psw))
            .post("/auth")
            .then()
            .log().all()
            .statusCode(200)
            .extract()
            .`as`(AuthRs::class.java)
    }
}