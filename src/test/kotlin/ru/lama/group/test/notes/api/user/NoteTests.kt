package ru.lama.group.test.notes.api.user

import jdk.internal.vm.Continuation.isPinned
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

    @DisplayName("Получение списка заметок")
    @Test
    fun getNote(){
        val noteOne = createNoteRq()
        val noteTwo = createNoteRq()
        val noteThree = createNoteRq()
        noteSteps.createNote(noteOne)
        noteSteps.createNote(noteTwo)
        var response = noteSteps.getNote()

        assertThat(response.size).isEqualTo(2)

        noteSteps.createNote(noteThree)
        response = noteSteps.getNote()

        assertThat(response.size).isEqualTo(3)
    }

    @DisplayName("Обновление заметки")
    @Test
    fun updateNote() {
        val noteOne = createNoteRq()
        val originalNote = noteSteps.createNote(noteOne)

        val updatedNote = originalNote.copy()

        if (updatedNote.isPinned)
            updatedNote.isPinned = false
        else updatedNote.isPinned = true

        if (updatedNote.color == "color-one")
            updatedNote.color = "color-two"
        else updatedNote.color = "color-one"

        noteSteps.updateNote(updatedNote)
    }
}