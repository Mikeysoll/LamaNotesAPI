package ru.lama.group.test.notes.api.user

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.lama.group.test.notes.UserRequestBuilder
import ru.lama.group.test.notes.base.TestBase
import ru.lama.group.test.notes.steps.UserSteps

class UserTests : TestBase() {

    private val userSteps = UserSteps()

    @Test
    fun `create user`() {
        val request = UserRequestBuilder.createUserRq()
        val response = userSteps.createUser(request)

        assertEquals(204, response.statusCode)
    }
}