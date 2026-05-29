package ru.lama.group.test.client

import io.restassured.RestAssured.given
import io.restassured.common.mapper.TypeRef
import io.restassured.response.Response
import ru.lama.group.test.notes.api.rq.NoteRq
import ru.lama.group.test.notes.api.rs.NoteRs
import ru.lama.group.test.base.baseRequestSpec
import ru.lama.group.test.context.Context
import ru.lama.group.test.notes.api.rs.NoteContentRs

class NoteApiClient(
    private val context: Context
) {
    fun createNote(request: NoteRq): NoteRs {
        return given()
            .spec(baseRequestSpec())
            .header("Authorization", "Bearer ${context.token}")
            .body(request)
            .post("/note")
            .then()
            .extract()
            .`as`(NoteRs::class.java)
    }

    fun getNote(): List<NoteRs> {
        return given()
            .spec(baseRequestSpec())
            .header("Authorization", "Bearer ${context.token}")
            .queryParam("search", "")
            .get("/note")
            .then()
            .extract()
            .`as`(object : TypeRef<List<NoteRs>>() {})
    }

    fun updateNote(updatedNote: NoteRs): Response {
        return given()
            .spec(baseRequestSpec())
            .header("Authorization", "Bearer ${context.token}")
            .body(updatedNote)
            .put("/note")
            .then()
            .extract()
            .response()
    }

    fun getNoteContent(id: String): NoteContentRs {
        return given()
            .spec(baseRequestSpec())
            .header("Authorization", "Bearer ${context.token}")
            .queryParam("noteId", id)
            .get("/note/content")
            .then()
            .extract()
            .`as`(object : TypeRef<NoteContentRs>() {})
    }
}