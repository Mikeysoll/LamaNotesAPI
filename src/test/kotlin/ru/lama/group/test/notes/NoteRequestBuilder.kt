package ru.lama.group.test.notes

import ru.lama.group.test.notes.api.rq.CreateNoteRq
import java.util.*

object NoteRequestBuilder {

    val colors = listOf("color-one", "color-two", "color-three", "color-four", "color-five")
    val types = listOf("NOTE", "LIST", "text-two", "text-three")

    fun createNoteRq(
        title: String = "ATest" + UUID.randomUUID().toString().replace("-", "").take(10),
        content: String = "ATest" + UUID.randomUUID().toString().replace("-", "").take(10),
        type: String = types.random(),
        folderId: String? = null,
        color: String = colors.random(),
    ): CreateNoteRq {
        return CreateNoteRq(
            title = title,
            content = content,
            color = color,
            folderId = folderId,
            type = type,
        )
    }


}