package ru.lama.group.test.notes.client

import io.restassured.RestAssured.given
import io.restassured.response.Response
import ru.lama.group.test.notes.api.rq.UserRq
import ru.lama.group.test.notes.api.rs.UserRs
import ru.lama.group.test.notes.base.baseRequestSpec

class UserApiClient {

    fun addUser(request: UserRq): Response {
        return given()
            .spec(baseRequestSpec())
            .log().all()
            .body(request)
            .post("/user")
            .then()
            .log().all()
            .extract()
            .response()
    }
}