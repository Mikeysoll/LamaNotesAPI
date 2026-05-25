package ru.lama.group.test.notes.steps

import io.qameta.allure.Step
import io.restassured.response.Response
import ru.lama.group.test.notes.api.rq.UserRq
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

        return userApiClient.createUser(user)
    }

    @Step("Отправка запроса GET /user")
    fun getUser(): Response {
        return userApiClient.getUser()
    }
}