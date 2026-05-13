package ru.lama.group.test.notes.api.rq

data class CreateUserRq(
    val login: String,
    val psw: String,
    val name: String
)