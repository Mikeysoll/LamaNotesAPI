package ru.lama.group.test.notes.client

import io.restassured.RestAssured.given
import io.restassured.response.Response
import ru.lama.group.test.notes.api.rq.ResetPasswordRq
import ru.lama.group.test.notes.api.rq.UserRq
import ru.lama.group.test.notes.api.rs.UserRs
import ru.lama.group.test.notes.base.baseRequestSpec
import ru.lama.group.test.notes.context.Context

class UserApiClient(
    private val context: Context
) {
    fun createUser(request: UserRq): Response {
        return given()
            .spec(baseRequestSpec())
            .body(request)
            .post("/user")
            .then()
            .extract()
            .response()
    }

    fun getUser(): UserRs {
        return given()
            .spec(baseRequestSpec())
            .header("Authorization", "Bearer ${context.token}")
            .get("/user")
            .then()
            .extract()
            .`as`(UserRs::class.java)
    }

    fun resetPassword(resetPasswordRq: ResetPasswordRq): Response {
        return given()
            .spec(baseRequestSpec())
            .header("Authorization", "Bearer ${context.token}")
            .body(resetPasswordRq)
            .post("/user/reset")
            .then()
            .extract()
            .response()
    }

/*    fun deleteUsers(): Response {
        return given
            .spec(baseRequestSpec())

    }*/
}