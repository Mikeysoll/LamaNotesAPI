package ru.lama.group.test.notes.api.rs

import java.time.Instant

data class NoteRs(
    val id: String,
    val updatedAt: Instant,
    val title: String,
    val preview: String,
    var color: String,
    var isPinned: Boolean,
    val type: String,
    val shareSettings: ShareSettings,
    val userId: String,
    val publicUrls: List<PublicUrls>,
)

data class ShareSettings(
    val groups: List<Groups>,
    val users: List<Users>,
)

data class Groups(
    val id : String,
    val name: String,
    val isReadOnly: Boolean,
)

data class Users(
    val id : String,
    val name: String,
    val isReadOnly: Boolean,
)

data class PublicUrls(
    val id: String,
    val createdAt: Instant,
    val isActive: Boolean,
    val url: String,
)