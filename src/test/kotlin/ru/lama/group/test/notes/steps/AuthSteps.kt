package ru.lama.group.test.notes.steps

import io.qameta.allure.Step
import ru.lama.group.test.notes.api.rs.AuthRs
import ru.lama.group.test.notes.client.AuthApiClient

class AuthSteps(
    private val authApiClient: AuthApiClient,
) {
    @Step("Отправка запроса /auth")
    fun auth(): AuthRs {
        return authApiClient.auth()
    }


}