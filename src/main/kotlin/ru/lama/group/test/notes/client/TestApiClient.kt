package ru.lama.group.test.notes.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "test-api", url = "\${api.base-url}")
interface TestApiClient {

    @GetMapping("/api/test/{id}")
    fun getTestById(@PathVariable("id") id: String): String

    @GetMapping("/api/health")
    fun getHealth(): String
}
