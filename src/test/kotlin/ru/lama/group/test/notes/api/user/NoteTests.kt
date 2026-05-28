package ru.lama.group.test.notes.api.user

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import ru.lama.group.test.notes.NoteRequestBuilder.createNoteRq
import ru.lama.group.test.notes.api.constants.Types
import ru.lama.group.test.notes.base.TestBase
import ru.lama.group.test.notes.client.NoteApiClient
import ru.lama.group.test.notes.steps.NoteSteps

class NoteTests : TestBase() {

    private val noteApiClient = NoteApiClient(context)
    private val noteSteps = NoteSteps(noteApiClient)

    @DisplayName("Создание заметок разных типов")
    @ParameterizedTest(name = "Заметка типа {0}")
    @EnumSource(Types::class)
    fun addNote(type: Types) {
        val request = createNoteRq(type = type)
        val notes = noteSteps.createNote(request)

        assertThat(notes.title).isEqualTo(request.title)
        assertThat(notes.color).isEqualTo(request.color)
        assertThat(notes.type).isEqualTo(request.type)
    }

    @DisplayName("Получение заметки из списка")
    @Test
    fun getNoteFromList() {
        val request = createNoteRq()
        val createdNote = noteSteps.createNote(request)
        val response = noteSteps.getNote()

        assertThat(response.find { it.title == createdNote.title } ).isEqualTo(createdNote)
    }
}