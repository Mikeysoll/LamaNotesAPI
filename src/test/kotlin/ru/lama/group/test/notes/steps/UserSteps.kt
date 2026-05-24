package ru.lama.group.test.notes.steps

import io.qameta.allure.Step
import io.restassured.response.Response
import ru.lama.group.test.notes.api.rq.CreateUserRq
import ru.lama.group.test.notes.client.UserApiClient
import ru.lama.group.test.notes.context.Context

class UserSteps(
    private val userApiClient: UserApiClient,
    private val context: Context
) {

    @Step("Отправка запроса POST /user")
    fun createUser(user: CreateUserRq): Response {

        context.login = user.login
        context.psw = user.psw

        return userApiClient.addUser(user)
    }
}