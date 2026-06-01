package ru.lama.group.test.client

import io.restassured.RestAssured.given
import io.restassured.common.mapper.TypeRef
import io.restassured.response.Response
import ru.lama.group.test.base.baseRequestSpec
import ru.lama.group.test.context.Context
import ru.lama.group.test.notes.api.rq.NoteRq
import ru.lama.group.test.notes.api.dto.NoteContent
import ru.lama.group.test.notes.api.rs.CountRs
import ru.lama.group.test.notes.api.rs.NoteRs
import java.util.ResourceBundle

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

    fun getNoteContent(id: String): NoteContent {
        return given()
            .spec(baseRequestSpec())
            .header("Authorization", "Bearer ${context.token}")
            .queryParam("noteId", id)
            .get("/note/content")
            .then()
            .extract()
            .`as`(object : TypeRef<NoteContent>() {})
    }

    fun updateNoteContent(request: NoteContent): Response {
        return given()
            .spec(baseRequestSpec())
            .header("Authorization", "Bearer ${context.token}")
            .body(request)
            .put("/note/content")
            .then()
            .extract()
            .response()
    }

    fun getNotesCount(): CountRs {
        return given()
            .spec(baseRequestSpec())
            .header("Authorization", "Bearer ${context.token}")
            .get("/note/counter")
            .then()
            .extract()
            .`as`(object : TypeRef<CountRs>() {})
    }

    fun deleteNote(id: String): Response {
        return given()
            .spec(baseRequestSpec())
            .header("Authorization", "Bearer ${context.token}")
            .queryParam("id", id)
            .delete("/note/$id")
            .then()
            .extract()
            .response()
    }

    fun restoreNote(id: String) : Response {
        return given()
            .spec(baseRequestSpec())
            .header("Authorization", "Bearer ${context.token}")
            .queryParam("id", id)
            .post("/note/$id")
            .then()
            .extract()
            .response()
    }
}