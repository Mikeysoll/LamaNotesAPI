package ru.lama.group.test.notes.steps

import io.qameta.allure.Step
import io.restassured.response.Response
import ru.lama.group.test.notes.api.rq.CreateUserRq
import ru.lama.group.test.notes.client.UserApiClient

class UserSteps {

    private val userApiClient = UserApiClient()

    @Step("Отправка запроса POST /user")
    fun createUser(user: CreateUserRq): Response {
        return userApiClient.addUser(user)
    }
}