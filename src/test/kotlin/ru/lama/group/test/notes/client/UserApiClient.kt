package ru.lama.group.test.notes.client

import io.restassured.RestAssured.given
import io.restassured.response.Response
import ru.lama.group.test.notes.api.rq.UserRq
import ru.lama.group.test.notes.base.baseRequestSpec

class UserApiClient {

    fun createUser(request: UserRq): Response {
        return given()
            .spec(baseRequestSpec())
            .log().all()
            .body(request)
            .post("/user")
            .then()
            .log().all()
            .statusCode(204)
            .extract()
            .response()
    }
    fun getUser(): Response {
        return given()
            .spec(baseRequestSpec())
            .log().all()
            .get("/user")
            .then()
            .log().all()
            .statusCode(200)
            .extract()
            .response()
    }
}