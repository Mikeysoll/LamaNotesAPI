package ru.lama.group.test.base

import io.restassured.builder.RequestSpecBuilder
import io.restassured.specification.RequestSpecification

fun baseRequestSpec(): RequestSpecification {
    return RequestSpecBuilder()
        .setBaseUri("https://lama-notes.ru")
        .setBasePath("/api/app/notes")
        .addHeader("X-Project-Token", "VFsQEvgwiDYwG_9B1KaZAOph4YzrwcmUFnEku2KOJDWmoOtdYELsbAncUwCsVfkg")
        .addHeader("Content-Type", "application/json")
        .build()
        .log().all()
}