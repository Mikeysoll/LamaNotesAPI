package ru.lama.group.test.notes.config

import feign.Logger
import feign.Request
import feign.Response
import java.nio.charset.StandardCharsets

class MinimalFeignLogger : Logger() {

    override fun logRequest(configKey: String, logLevel: Level, request: Request) {
        println("---> ${request.httpMethod()} ${request.url()}")
        request.body()?.let { bodyBytes ->
            val bodyString = String(bodyBytes, StandardCharsets.UTF_8)
            println(bodyString)
        }
        val bodySize = request.body()?.size ?: 0
        println("---> END ${request.httpMethod()} ($bodySize-byte body)")
    }

    override fun logAndRebufferResponse(
        configKey: String,
        logLevel: Level,
        response: Response,
        elapsedTime: Long
    ): Response {
        val bodyBytes = response.body()?.asInputStream()?.readBytes()
        val bodyString = bodyBytes?.let { String(it, StandardCharsets.UTF_8) } ?: ""

        println("<--- ${response.status()} ${response.reason()} (${elapsedTime}ms)")
        if (bodyString.isNotBlank()) {
            println(bodyString)
        }
        println("<--- END HTTP (${bodyString.length}-byte body)")

        return response.toBuilder()
            .body(bodyBytes)
            .build()
    }

    override fun log(configKey: String, format: String, vararg args: Any?) {

    }

}