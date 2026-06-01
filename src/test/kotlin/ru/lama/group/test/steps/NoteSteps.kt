package ru.lama.group.test.steps

import io.qameta.allure.Step
import io.restassured.response.Response
import ru.lama.group.test.notes.api.rq.NoteRq
import ru.lama.group.test.notes.api.rs.NoteRs
import ru.lama.group.test.client.NoteApiClient
import ru.lama.group.test.notes.api.dto.NoteContent
import ru.lama.group.test.notes.api.rs.CountRs

class NoteSteps(
    private val noteApiClient: NoteApiClient,
) {
    @Step("Отправка запроса POST /note")
    fun createNote(noteRq: NoteRq): NoteRs {
        return noteApiClient.createNote(noteRq)
    }

    @Step("Отправка запроса GET /note")
    fun getNote(): List<NoteRs> {
        return noteApiClient.getNote()
    }

    @Step("Отправка запроса PUT /note")
    fun updateNote(updatedNote: NoteRs): Response {
        return noteApiClient.updateNote(updatedNote)
    }

    @Step("Отправка запроса GET /note/content")
    fun getNoteContent(id: String): NoteContent {
        return noteApiClient.getNoteContent(id)
    }

    @Step("Отправка запроса PUT /note/content")
    fun updateNoteContent(request: NoteContent): Response{
        return noteApiClient.updateNoteContent(request)
    }

    @Step("Отправка запроса GET /note/counter")
    fun getNotesCount() : CountRs{
        return noteApiClient.getNotesCount()
    }
}
