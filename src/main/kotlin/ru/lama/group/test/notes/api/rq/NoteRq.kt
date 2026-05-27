package ru.lama.group.test.notes.api.rq

import ru.lama.group.test.notes.api.constants.Colors
import ru.lama.group.test.notes.api.constants.Types

data class NoteRq(
    val title: String,
    val content: String,
    val color: Colors,
    val folderId: String?,
    val type: Types,
)