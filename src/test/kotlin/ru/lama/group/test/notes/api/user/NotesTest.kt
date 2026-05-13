package ru.lama.group.test.notes.api.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ru.lama.group.test.notes.client.UserApiClient

@SpringBootTest
class NotesTest {

    @Autowired
    private lateinit var userApiClient: UserApiClient

//    @Test
//    fun `test checkHealth returns expected result`() {
//        val health = testApiClient.getHealth()
//        assertEquals(health, "")
//    }
}