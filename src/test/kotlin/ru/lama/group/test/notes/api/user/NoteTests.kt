package ru.lama.group.test.notes.api.user

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import ru.lama.group.test.notes.UserRequestBuilder
import ru.lama.group.test.notes.api.rq.NoteRq
import ru.lama.group.test.notes.base.TestBase
import ru.lama.group.test.notes.client.AuthApiClient
import ru.lama.group.test.notes.client.NoteApiClient
import ru.lama.group.test.notes.client.UserApiClient
import ru.lama.group.test.notes.context.Context
import ru.lama.group.test.notes.steps.AuthSteps
import ru.lama.group.test.notes.steps.UserSteps
import java.util.*

class NoteTests: TestBase() {

    private val noteApiClient = NoteApiClient(context)

    @DisplayName("Создание заметки типа NOTE")
    @Test
    fun addNote() {
        val request = NoteRq(
            title = "ATestTitle" + UUID.randomUUID().toString().replace("-", "").take(10),
            content = "ATestContent" + UUID.randomUUID().toString().replace("-", "").take(10),
            color = listOf("color-one", "color-two", "color-three", "color-four", "color-five").random(),
            folderId = null,
            type = "NOTE"
        )

        noteApiClient.createNote(request)
        val notes = noteApiClient.getNotes()

        val exists = notes.any { it.title == request.title }
        assertTrue(exists)

    }

    @DisplayName("Создание заметки типа LIST")
    @Test
    fun addList() {
        val request = NoteRq(
            title = "ATestTitle" + UUID.randomUUID().toString().replace("-", "").take(10),
            content = "ATestContent" + UUID.randomUUID().toString().replace("-", "").take(10),
            color = listOf("color-one", "color-two", "color-three", "color-four", "color-five").random(),
            folderId = null,
            type = "LIST"
        )

        noteApiClient.createNote(request)
        val notes = noteApiClient.getNotes()

        val exists = notes.any { it.title == request.title }
        assertTrue(exists)
    }

    @DisplayName("Создание заметки типа WISH_LIST")
    @Test
    fun addWishList() {
        val request = NoteRq(
            title = "ATestTitle" + UUID.randomUUID().toString().replace("-", "").take(10),
            content = "ATestContent" + UUID.randomUUID().toString().replace("-", "").take(10),
            color = listOf("color-one", "color-two", "color-three", "color-four", "color-five").random(),
            folderId = null,
            type = "WISH_LIST"
        )

        noteApiClient.createNote(request)
        val notes = noteApiClient.getNotes()

        val exists = notes.any { it.title == request.title }
        assertTrue(exists)
    }
}