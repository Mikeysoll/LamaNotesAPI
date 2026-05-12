package ru.lama.group.test.notes.client

import feign.Headers
import feign.Param
import feign.RequestLine

interface TestApiClient {

    @RequestLine("GET /api/test/{id}")
    fun getTestById(@Param("id") id: String): String

    @RequestLine("GET /api/health")
    fun getHealth(): String
}
