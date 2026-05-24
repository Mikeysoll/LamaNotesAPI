package ru.lama.group.test.notes.api.rs

import kotlin.time.Instant

data class GetNoteRs(

    val id: String,
    val updateAt: String,
    val title: String,
    val preview: String,
    val color: String,
    val isPinned: Boolean,
    val folderId: String,
    val type: String,
    val userId: String,
    val shareSettings: ShareSettings,
    val publicUrls: PublicUrls,
)

data class ShareSettings(
    val groups: Groups,
    val users: Users,
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