package ru.lama.group.test.notes.api.rs

import kotlin.time.Instant

data class NoteContentRq(
    val id: String,
    val value: String,
    val preview: String,
    val notifications: List<NotificationsRq>,
)

data class NotificationsRq(
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