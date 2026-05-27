package ru.lama.group.test.notes.api.user

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import ru.lama.group.test.notes.NoteRequestBuilder.createNoteRq
import ru.lama.group.test.notes.api.constants.Colors
import ru.lama.group.test.notes.api.constants.Types
import ru.lama.group.test.notes.api.rq.NoteRq
import ru.lama.group.test.notes.base.TestBase
import ru.lama.group.test.notes.client.NoteApiClient
import ru.lama.group.test.notes.steps.NoteSteps
import java.awt.Color
import java.util.*

class NoteTests : TestBase() {

    private val noteApiClient = NoteApiClient(context)
    private val noteSteps = NoteSteps(noteApiClient)

    @DisplayName("Создание заметки типа NOTE")
    @Test
    fun addNote() {
        val request = createNoteRq(type = Types.NOTE, color = Colors.entries.random())

        val notes = noteSteps.createNote(request)
        assertThat(notes.title == request.title)
        assertThat(notes.color == request.color.value)
        assertThat(notes.type == request.type.value)
    }

    @DisplayName("Создание заметки типа LIST")
    @Test
    fun addList() {
        val request = createNoteRq(type = Types.LIST, color = Colors.entries.random())

        val notes = noteSteps.createNote(request)
        assertThat(notes.title == request.title)
        assertThat(notes.color == request.color.value)
        assertThat(notes.type == request.type.value)
    }

    @DisplayName("Создание заметки типа WISH_LIST")
    @Test
    fun addWishList() {
        val request = createNoteRq(type = Types.WISH_LIST, color = Colors.entries.random())

        val notes = noteSteps.createNote(request)
        assertThat(notes.title == request.title)
        assertThat(notes.color == request.color.value)
        assertThat(notes.type == request.type.value)
    }

    @DisplayName("Получение списка заметок")
    @Test
    fun getNoteList() {
        val request = createNoteRq()
        val notes = noteSteps.createNote(request)


    }
}
