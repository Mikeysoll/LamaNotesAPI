package ru.lama.group.test.notes.api.user

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import ru.lama.group.test.notes.NoteRequestBuilder.createNoteRq
import ru.lama.group.test.notes.api.constants.Types
import ru.lama.group.test.notes.base.TestBase
import ru.lama.group.test.notes.client.NoteApiClient
import ru.lama.group.test.notes.steps.NoteSteps

class NoteTests : TestBase() {

    private val noteApiClient = NoteApiClient(context)
    private val noteSteps = NoteSteps(noteApiClient)

    @DisplayName("Создание заметки типа NOTE")
    @Test
    fun addNote() {
        val request = createNoteRq(type = Types.NOTE)
        val notes = noteSteps.createNote(request)

        assertThat(notes.title == request.title)
        assertThat(notes.color == request.color)
        assertThat(notes.type == request.type)
    }

    @DisplayName("Создание заметки типа LIST")
    @Test
    fun addList() {
        val request = createNoteRq(type = Types.LIST)
        val notes = noteSteps.createNote(request)

        assertThat(notes.title).isEqualTo(request.title)
        assertThat(notes.color).isEqualTo(request.color)
        assertThat(notes.type).isEqualTo(request.type)
    }

    @DisplayName("Создание заметки типа WISH_LIST")
    @Test
    fun addWishList() {
        val request = createNoteRq(type = Types.WISH_LIST)
        val notes = noteSteps.createNote(request)

        assertThat(notes.title == request.title)
        assertThat(notes.color == request.color)
        assertThat(notes.type == request.type)
    }

    @DisplayName("Получение заметки из списка")
    @Test
    fun getNote() {
        val request = createNoteRq()
        val note = noteSteps.createNote(request)
        val response = noteSteps.getNote()

        assertThat(response.find { it.title == note.title }!!.title).isNotNull.isEqualTo(note.title)
    }
}
