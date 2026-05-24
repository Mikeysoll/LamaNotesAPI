package ru.lama.group.test.notes.api.rq

data class NoteRq(
    val title: String,
    val content: String,
    val color: String,
    val folderId: String?,
    val type: String,
)