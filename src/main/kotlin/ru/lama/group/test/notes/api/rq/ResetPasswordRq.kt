package ru.lama.group.test.notes.api.rq

data class ResetPasswordRq(
    val psw: String,
    val newPsw: String
)