package ru.lama.group.test.notes.api.user

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import ru.lama.group.test.notes.UserRequestBuilder
import ru.lama.group.test.notes.client.AuthApiClient
import ru.lama.group.test.notes.client.UserApiClient
import ru.lama.group.test.notes.context.Context
import ru.lama.group.test.notes.steps.AuthSteps
import ru.lama.group.test.notes.steps.UserSteps
import kotlin.test.assertTrue

class AuthTests {

    private val context = Context()
    private val authSteps = AuthSteps(AuthApiClient(context), context)
    private val userSteps = UserSteps(UserApiClient(context), context)

    @DisplayName("Аутентификация и получение токена")
    @Test
    fun auth() {
        val request = UserRequestBuilder.createUserRq()
        userSteps.createUser(request)

        val response = authSteps.auth()
        context.token = response.token

        assertTrue { response.token.isNotEmpty() }
    }
}