package ru.lama.group.test.notes

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ru.lama.group.test.notes.client.TestApiClient
import kotlin.test.assertEquals

@SpringBootTest
class NotesTest {

    @Autowired
    private lateinit var testApiClient: TestApiClient

    @Test
    fun `test checkHealth returns expected result`() {
        val health = testApiClient.getHealth()
        assertEquals(health, "")
    }
}
