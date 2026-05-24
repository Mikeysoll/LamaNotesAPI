package ru.lama.group.test.notes.api.rq

data class CreateNoteRq(
    val title: String,
    val content: String,
    val color: String,
    val folderId: String?,
    val type: String,
)