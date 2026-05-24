package ru.lama.group.test.notes

import ru.lama.group.test.notes.api.rq.UserRq
import java.util.UUID

object UserRequestBuilder {

    fun createUserRq(
        login: String = "ATestLogin" + UUID.randomUUID().toString().replace("-", ""),
        psw: String = "ATestPsw" + UUID.randomUUID().toString().replace("-", ""),
        name: String = "ATestName" + UUID.randomUUID().toString().replace("-", "")
    ): UserRq {
        return UserRq(
            login = login,
            psw = psw,
            name = name
        )
    }
}