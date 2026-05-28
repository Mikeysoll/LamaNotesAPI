package ru.lama.group.test.notes

import ru.lama.group.test.notes.api.constants.Colors
import ru.lama.group.test.notes.api.constants.Types
import ru.lama.group.test.notes.api.rq.NoteRq
import java.util.*

object NoteRequestBuilder {

    fun createNoteRq(
        title: String = "ATest" + UUID.randomUUID().toString().replace("-", "").take(10),
        content: String = "ATest" + UUID.randomUUID().toString().replace("-", "").take(10),
        type: Types = Types.entries.random(),
        folderId: String? = null,
        color: Colors = Colors.entries.random()
    ): NoteRq {
        return NoteRq(
            title = title,
            content = content,
            color = color.value,
            folderId = folderId,
            type = type.value,
        )
    }
}