package ru.lama.group.test.notes.steps

import feign.Response
import io.qameta.allure.Step
import org.springframework.stereotype.Component
import ru.lama.group.test.notes.api.rq.CreateUserRq
import ru.lama.group.test.notes.client.UserApiClient

@Component
class UserSteps(
    private var userApiClient: UserApiClient
) {
    @Step("отправка запроса POST /user")
    fun createUser(user: CreateUserRq): Response {
        return userApiClient.addUser(user.login, user.psw, user.name)
    }
}