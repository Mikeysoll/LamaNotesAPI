package ru.lama.group.test.notes.base

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import ru.lama.group.test.notes.UserRequestBuilder
import ru.lama.group.test.notes.client.AuthApiClient
import ru.lama.group.test.notes.client.UserApiClient
import ru.lama.group.test.notes.context.Context
import ru.lama.group.test.notes.steps.AuthSteps
import ru.lama.group.test.notes.steps.UserSteps

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

/*    @AfterEach
    fun cleanUp(){

    }*/
}