package ru.lama.group.test.notes.steps

import io.qameta.allure.Step
import io.restassured.response.Response
import ru.lama.group.test.notes.api.rq.ResetPasswordRq
import ru.lama.group.test.notes.api.rq.UserRq
import ru.lama.group.test.notes.api.rs.UserRs
import ru.lama.group.test.notes.client.UserApiClient
import ru.lama.group.test.notes.context.Context

class UserSteps(
    private val userApiClient: UserApiClient,
    private val context: Context
) {

    @Step("Отправка запроса POST /user")
    fun createUser(user: UserRq): Response {

        context.login = user.login
        context.psw = user.psw
        context.name = user.name

        return userApiClient.createUser(user)
    }

    @Step("Отправка запроса GET /user")
    fun getUser(): UserRs {
        return userApiClient.getUser()
    }

    @Step("Отправка запроса POST /user/reset")
    fun resetPassword(resetPasswordRq: ResetPasswordRq): Response {
        return userApiClient.resetPassword(resetPasswordRq)
    }
}