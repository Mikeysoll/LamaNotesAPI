package ru.lama.group.test.api.note

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.jsoup.Jsoup
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import ru.lama.group.test.base.TestBase
import ru.lama.group.test.builders.NoteRequestBuilder
import ru.lama.group.test.client.NoteApiClient
import ru.lama.group.test.notes.api.constants.Colors
import ru.lama.group.test.notes.api.constants.Types
import ru.lama.group.test.notes.api.dto.NoteContent
import ru.lama.group.test.steps.NoteSteps
import java.util.UUID

class NoteTests : TestBase() {

    private val noteApiClient = NoteApiClient(context)
    private val noteSteps = NoteSteps(noteApiClient)

    @ParameterizedTest(name = "Заметка типа {0}")
    @DisplayName("Создание заметок разных типов")
    @EnumSource(Types::class)
    fun `add note`(type: Types) {
        val request = NoteRequestBuilder.createNoteRq(type = type)
        val notes = noteSteps.createNote(request)

        assertThat(notes.title)
            .isEqualTo(request.title)
        assertThat(notes.color)
            .isEqualTo(request.color)
        assertThat(notes.type)
            .isEqualTo(request.type)
    }

    @Test
    @DisplayName("Получение заметки из списка")
    fun `get note from list`() {
        val request = NoteRequestBuilder.createNoteRq()
        val createdNote = noteSteps.createNote(request)
        val response = noteSteps.getNote()

        assertThat(response.find { it.title == createdNote.title })
            .isEqualTo(createdNote)
    }

    @Test
    @DisplayName("Получение списка заметок")
    fun `get note`() {
        val noteOne = NoteRequestBuilder.createNoteRq()
        val noteTwo = NoteRequestBuilder.createNoteRq()
        val noteThree = NoteRequestBuilder.createNoteRq()
        noteSteps.createNote(noteOne)
        noteSteps.createNote(noteTwo)
        var response = noteSteps.getNote()

        Assertions.assertThat(response.size).isEqualTo(2)

        noteSteps.createNote(noteThree)
        response = noteSteps.getNote()

        Assertions.assertThat(response.size)
            .isEqualTo(3)
    }

    @Test
    @DisplayName("Обновление заметки")
    fun `update note`() {
        val noteOne = NoteRequestBuilder.createNoteRq(
            color = Colors.COLOR_ONE,
        )
        val originalNote = noteSteps.createNote(noteOne)

        val updatedNote = originalNote.copy(
            color = Colors.COLOR_TWO.value,
            isPinned = true
        )

        updatedNote.color = "color-two"
        updatedNote.isPinned = true

        noteSteps.updateNote(updatedNote)

        val response = noteSteps.getNote().find { it.id == updatedNote.id }

        Assertions.assertThat(response?.color)
            .isNotEqualTo(originalNote.color)
        Assertions.assertThat(response?.isPinned)
            .isNotEqualTo(originalNote.isPinned)
    }

    @Test
    @DisplayName("Проверка закрепления заметки")
    fun `pinned note`() {
        val noteOne = NoteRequestBuilder.createNoteRq(title = "NoteOne")
        val noteTwo = NoteRequestBuilder.createNoteRq(title = "NoteTwo")
        val noteThree = NoteRequestBuilder.createNoteRq(title = "NoteThree")

        noteSteps.createNote(noteOne)
        noteSteps.createNote(noteTwo)
        noteSteps.createNote(noteThree)

        val responseBeforePin = noteSteps.getNote()
        val indexBeforePin = responseBeforePin.indexOfFirst { it.title == "NoteOne" }

        Assertions.assertThat(indexBeforePin).isEqualTo(2)

        val updatedNote = responseBeforePin.find { it.title == "NoteOne" }
        updatedNote!!.isPinned = true
        noteSteps.updateNote(updatedNote)

        val responseAfterPin = noteSteps.getNote()
        val indexAfterPin = responseAfterPin.indexOfFirst { it.title == "NoteOne" }

        Assertions.assertThat(indexAfterPin)
            .isEqualTo(0)
    }

    @Test
    @DisplayName("Получение текста заметки")
    fun `get note content`() {
        val noteTwoRs = noteSteps.createNote(NoteRequestBuilder.createNoteRq())
        val noteOneRq = NoteRequestBuilder.createNoteRq()
        val noteOneRs = noteSteps.createNote(noteOneRq)
        val noteOneContentRq = noteOneRq.content

        val noteOneContentRs = noteSteps.getNoteContent(noteOneRs.id)

        assertThat(noteOneContentRs.id)
            .isEqualTo(noteOneRs.id)
        assertThat(noteOneContentRq)
            .isEqualTo(Jsoup.parse(noteOneContentRs.value).text())
    }

    @Test
    @DisplayName("Обновление текста заметки")
    fun `update note content`() {
        val noteOneRq = NoteRequestBuilder.createNoteRq()
        val noteOneRs = noteSteps.createNote(noteOneRq)

        val noteOneContent = noteSteps.getNoteContent(noteOneRs.id)
        val noteNewContent = "ATest" + UUID.randomUUID().toString().replace("-", "").take(10)
        val noteNewPreview = noteNewContent.take(50)

        assertThat(noteOneRq.content)
            .isEqualTo(Jsoup.parse(noteOneContent.value).text())

        noteOneContent.value = noteOneContent.value.replace(
            Jsoup.parse(noteOneContent.value).text(),
            noteNewContent
        )
        noteOneContent.preview = noteNewPreview

        noteSteps.updateNoteContent(noteOneContent)

        val noteOneNew = noteSteps.getNoteContent(noteOneRs.id)

        assertThat(noteNewContent)
            .isEqualTo(Jsoup.parse(noteOneNew.value).text())
        assertThat(noteNewPreview)
            .isEqualTo(noteOneNew.preview)
    }

    @Test
    @DisplayName("Получение кол-ва заметок")
    fun `get count of notes`() {
        val noteRsList = (0..2).map {
            val noteRq = NoteRequestBuilder.createNoteRq()
            noteSteps.createNote(noteRq)
        }

        val count = noteSteps.getNotesCount()

        assertThat(count.count).isEqualTo(noteRsList.size)
    }

    @Test
    @DisplayName("Удаление заметки")
    fun `delete note`(){
        val noteRq = NoteRequestBuilder.createNoteRq()
        val noteRs = noteSteps.createNote(noteRq)
        var noteList = noteSteps.getNote()

        assertThat(noteRs).isEqualTo(noteList.find { it.id == noteRs.id })

        noteSteps.deleteNote(noteRs.id)
        noteList = noteSteps.getNote()

        assertThat(noteList.find { it.id == noteRs.id }).isEqualTo(null)
    }

    @Test
    @DisplayName("Восстановление удаленной заметки")
    fun `restore note`(){
        val noteRq = NoteRequestBuilder.createNoteRq()
        val noteRs = noteSteps.createNote(noteRq)
        noteSteps.deleteNote(noteRs.id)
        noteSteps.restoreNote(noteRs.id)
        val noteList = noteSteps.getNote()

        assertThat(noteRs.id).isEqualTo((noteList.find { it.id == noteRs.id })?.id)
    }
}













