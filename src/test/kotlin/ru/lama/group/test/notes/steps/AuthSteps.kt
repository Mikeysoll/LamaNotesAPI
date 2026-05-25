package ru.lama.group.test.notes.steps

import io.qameta.allure.Step
import ru.lama.group.test.notes.api.rs.AuthRs
import ru.lama.group.test.notes.client.AuthApiClient
import ru.lama.group.test.notes.context.Context

class AuthSteps(
    private val authApiClient: AuthApiClient,
    private val context: Context
) {
    @Step("Отправка запроса POST /auth")
    fun auth(): AuthRs {

        val response = authApiClient.auth()
        context.token = response.token

        return response
    }
}