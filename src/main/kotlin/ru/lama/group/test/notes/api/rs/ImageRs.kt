package ru.lama.group.test.notes.api.rs

import java.time.Instant

data class ImageRs(
    val id: String,
    val createdAt: Instant,
    val name: String,
    val size: Int,
)