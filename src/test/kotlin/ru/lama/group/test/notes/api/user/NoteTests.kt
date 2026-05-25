package ru.lama.group.test.notes.api.user

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import ru.lama.group.test.notes.api.rq.NoteRq
import ru.lama.group.test.notes.base.TestBase
import ru.lama.group.test.notes.client.NoteApiClient
import java.util.*

class NoteTests : TestBase() {

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

        val notes = noteApiClient.createNote(request)

        assertThat(notes.title == request.title)
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