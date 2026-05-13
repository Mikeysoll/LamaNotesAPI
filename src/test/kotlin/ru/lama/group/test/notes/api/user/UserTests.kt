package ru.lama.group.test.notes.api.user


import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ru.lama.group.test.notes.UserRequestBuilder
import ru.lama.group.test.notes.steps.UserSteps

@SpringBootTest
class UserTests {

    @Autowired
    private lateinit var userSteps: UserSteps

    @Test
    fun `create user`() {
        val request = UserRequestBuilder.createUserRq()
        val response = userSteps.createUser(request)

        assertEquals(204, response.status(), "Код ответа должен быть 204")
    }
}