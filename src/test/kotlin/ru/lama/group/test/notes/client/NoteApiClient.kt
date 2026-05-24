package ru.lama.group.test.notes.client

import io.restassured.RestAssured.given
import ru.lama.group.test.notes.api.rq.NoteRq
import ru.lama.group.test.notes.api.rs.NoteRs
import ru.lama.group.test.notes.base.baseRequestSpec
import ru.lama.group.test.notes.context.Context

class NoteApiClient(
    private val context: Context
) {
    fun createNote(request: NoteRq): NoteRs {
        return given()
            .spec(baseRequestSpec())
            .log().all()
            .header("Authorization", "Bearer ${context.token}")
            .body(request)
            .post("/note")
            .then()
            .log().all()
            .extract()
            .`as`(NoteRs::class.java)
    }

}