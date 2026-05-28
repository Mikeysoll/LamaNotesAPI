package ru.lama.group.test.notes.steps

import io.qameta.allure.Step
import io.restassured.response.Response
import ru.lama.group.test.notes.api.rq.NoteRq
import ru.lama.group.test.notes.api.rs.NoteRs
import ru.lama.group.test.notes.client.NoteApiClient

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
}
