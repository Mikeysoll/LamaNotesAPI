package ru.lama.group.test.api.auth

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import ru.lama.group.test.builders.UserRequestBuilder
import ru.lama.group.test.client.AuthApiClient
import ru.lama.group.test.client.UserApiClient
import ru.lama.group.test.context.Context
import ru.lama.group.test.steps.AuthSteps
import ru.lama.group.test.steps.UserSteps

class AuthTests {

    private val context = Context()
    private val authSteps = AuthSteps(AuthApiClient(context), context)
    private val userSteps = UserSteps(UserApiClient(context), context)

    @Test
    @DisplayName("Аутентификация и получение токена")
    fun auth() {
        val request = UserRequestBuilder.createUserRq()
        userSteps.createUser(request)

        val response = authSteps.auth()
        context.token = response.token

        Assertions.assertThat(response.token).isNotEmpty()
    }
}