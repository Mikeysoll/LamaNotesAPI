package ru.lama.group.test.notes.api.user

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import ru.lama.group.test.notes.UserRequestBuilder
import ru.lama.group.test.notes.api.rq.ResetPasswordRq
import ru.lama.group.test.notes.client.AuthApiClient
import ru.lama.group.test.notes.client.UserApiClient
import ru.lama.group.test.notes.context.Context
import ru.lama.group.test.notes.steps.AuthSteps
import ru.lama.group.test.notes.steps.UserSteps
import java.util.UUID

class UserTests {
    private val context = Context()
    private val userSteps = UserSteps(UserApiClient(context), context)
    private val authSteps = AuthSteps(AuthApiClient(context), context)
    private val authApiClient = AuthApiClient(context)

    @DisplayName("Создание пользователя")
    @Test
    fun createUser() {
        val request = UserRequestBuilder.createUserRq()
        val response = userSteps.createUser(request)

        assertEquals(204, response.statusCode())
    }

    @DisplayName("Создание и получение текущего пользователя")
    @Test
    fun getUser() {

        val request = UserRequestBuilder.createUserRq()
        userSteps.createUser(request)
        authSteps.auth()

        val response = userSteps.getUser()

        assertThat(context.login == response.login)
        assertThat(context.name == response.name)
    }

    @DisplayName("Смена пароля")
    @Test
    fun resetPassword() {
        val request = UserRequestBuilder.createUserRq()
        userSteps.createUser(request)
        authSteps.auth()

        val newPsw = "ATestPsw" + UUID.randomUUID().toString().replace("-", "").take(10)

        val resetPasswordRq = ResetPasswordRq(context.psw, newPsw)
        val response = userSteps.resetPassword(resetPasswordRq)

        authApiClient.authError()
        context.psw = newPsw
        authSteps.auth()


        assertEquals(204 , response.statusCode())


    }

}


































