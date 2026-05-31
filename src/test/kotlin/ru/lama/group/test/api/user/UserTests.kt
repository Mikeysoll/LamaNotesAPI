package ru.lama.group.test.api.user

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import ru.lama.group.test.builders.UserRequestBuilder
import ru.lama.group.test.client.AuthApiClient
import ru.lama.group.test.client.UserApiClient
import ru.lama.group.test.context.Context
import ru.lama.group.test.notes.api.rq.ResetPasswordRq
import ru.lama.group.test.steps.AuthSteps
import ru.lama.group.test.steps.UserSteps
import java.util.UUID

class UserTests {
    private val context = Context()
    private val userSteps = UserSteps(UserApiClient(context), context)
    private val authSteps = AuthSteps(AuthApiClient(context), context)
    private val authApiClient = AuthApiClient(context)

    @AfterEach
    fun cleanUp(){
        userSteps.deleteTestUsers()
    }

    @Test
    @DisplayName("Создание пользователя")
    fun `create user`() {
        val request = UserRequestBuilder.createUserRq()
        val response = userSteps.createUser(request)

        Assertions.assertThat(204)
            .isEqualTo(response.statusCode)
    }

    @Test
    @DisplayName("Создание и получение текущего пользователя")
    fun `get user`() {
        val request = UserRequestBuilder.createUserRq()
        userSteps.createUser(request)
        authSteps.auth()

        val response = userSteps.getUser()

        Assertions.assertThat(context.login)
            .isEqualTo(response.login)
        Assertions.assertThat(context.name)
            .isEqualTo(response.name)
    }

    @Test
    @DisplayName("Смена пароля")
    fun `reset password`() {
        val request = UserRequestBuilder.createUserRq()
        userSteps.createUser(request)
        authSteps.auth()

        val newPsw = "ATestPsw" + UUID.randomUUID().toString().replace("-", "").take(10)

        val resetPasswordRq = ResetPasswordRq(context.psw, newPsw)
        val response = userSteps.resetPassword(resetPasswordRq)

        authApiClient.authError()
        context.psw = newPsw
        authSteps.auth()

        Assertions.assertThat(204)
            .isEqualTo(response.statusCode)
    }
}