package ru.lama.group.test.notes.api.dto

import kotlin.time.Instant

data class NoteContent(
    val id: String,
    var value: String,
    var preview: String,
    val notifications: List<Notifications>,
)

data class Notifications(
    val startAt: Instant,
    val text: String,
    val id: String,
    val hour: Int,
    val minute: Int,
    val frequency: String,
    val month: Int,
    val day: Int,
    val isActive: Boolean,
    val year: Int,
)