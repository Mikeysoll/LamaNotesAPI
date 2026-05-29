package ru.lama.group.test.base

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import ru.lama.group.test.builders.UserRequestBuilder
import ru.lama.group.test.client.AuthApiClient
import ru.lama.group.test.client.UserApiClient
import ru.lama.group.test.context.Context
import ru.lama.group.test.steps.AuthSteps
import ru.lama.group.test.steps.UserSteps

open class TestBase {

    protected val context = Context()
    private val authSteps = AuthSteps(AuthApiClient(context), context)
    private val userSteps = UserSteps(UserApiClient(context), context)

    @BeforeEach
    fun setUp() {
        val request = UserRequestBuilder.createUserRq()
        userSteps.createUser(request)
        authSteps.auth()
    }

    @AfterEach
    fun cleanUp(){
        userSteps.deleteTestUsers()
    }
}
