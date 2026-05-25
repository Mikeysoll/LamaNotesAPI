package ru.lama.group.test.notes.api.rs

data class UserRs(
    val id: String,
    val createdAt: String,
    val login: String,
    val name: String,
    val tariff: Tariff,
    val telegramUrl: String,
)

data class Tariff(
    val id: String,
    val createdAt: String,
    val title: String,
    val description: String,
    val unit: String,
    val price: Double,
    val features: List<String>,
    val isArchived: Boolean,
    val isDefault: Boolean,
    val settings: TariffSettings,
)

data class TariffSettings(
    val maxNoteCount: Int,
    val maxFolderCount: Int
)