package ru.lama.group.test.notes.steps

import io.qameta.allure.Step
import ru.lama.group.test.notes.api.rq.NoteRq
import ru.lama.group.test.notes.api.rs.NoteRs
import ru.lama.group.test.notes.client.NoteApiClient
import ru.lama.group.test.notes.context.Context

class NoteSteps(
    private val noteApiClient: NoteApiClient,
) {

    @Step("Отправка запроса POST /note")
    fun createNote(noteRq: NoteRq): NoteRs {
        return noteApiClient.createNote(noteRq)
    }

}