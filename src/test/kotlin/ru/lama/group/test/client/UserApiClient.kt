package ru.lama.group.test.client

import io.restassured.RestAssured.given
import io.restassured.response.Response
import ru.lama.group.test.notes.api.rq.ResetPasswordRq
import ru.lama.group.test.notes.api.rq.UserRq
import ru.lama.group.test.notes.api.rs.UserRs
import ru.lama.group.test.base.baseJsonRequestSpec
import ru.lama.group.test.context.Context

class UserApiClient(
    private val context: Context
) {
    fun createUser(request: UserRq): Response {
        return given()
            .spec(baseJsonRequestSpec())
            .body(request)
            .post("/user")
            .then()
            .extract()
            .response()
    }

    fun getUser(): UserRs {
        return given()
            .spec(baseJsonRequestSpec())
            .header("Authorization", "Bearer ${context.token}")
            .get("/user")
            .then()
            .extract()
            .`as`(UserRs::class.java)
    }

    fun resetPassword(resetPasswordRq: ResetPasswordRq): Response {
        return given()
            .spec(baseJsonRequestSpec())
            .header("Authorization", "Bearer ${context.token}")
            .body(resetPasswordRq)
            .post("/user/reset")
            .then()
            .extract()
            .response()
    }

    fun deleteTestUsers(): Response {
        return given()
            .spec(baseJsonRequestSpec())
            .header("Authorization", "Bearer ${context.token}")
            .delete("/adm/user")
            .then()
            .extract()
            .response()
    }
}
