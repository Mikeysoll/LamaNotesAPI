package ru.lama.group.test.notes.api.rq

import ru.lama.group.test.notes.api.rs.Groups
import ru.lama.group.test.notes.api.rs.PublicUrls
import ru.lama.group.test.notes.api.rs.ShareSettings
import ru.lama.group.test.notes.api.rs.Users

class NoteUpdateRq(
    val id: String,
    val updatedAt: String,
    val title: String,
    val preview: String,
    val color: String,
    val isPinned: Boolean,
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