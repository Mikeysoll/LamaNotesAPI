package ru.lama.group.test.notes.base

import io.restassured.RestAssured
import org.junit.jupiter.api.BeforeAll

open class TestBase {

    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() {
            RestAssured.requestSpecification = RequestSpec.baseRequestSpec()
        }
    }
}