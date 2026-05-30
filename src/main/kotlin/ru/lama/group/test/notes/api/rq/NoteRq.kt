package ru.lama.group.test.notes.api.rq

data class NoteRq(
    val title: String,
    var content: String,
    val color: String,
    val folderId: String?,
    val type: String,
)