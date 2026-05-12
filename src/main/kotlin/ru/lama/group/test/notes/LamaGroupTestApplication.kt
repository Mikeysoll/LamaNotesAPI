package ru.lama.group.test.notes

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class LamaGroupTestApplication

fun main(args: Array<String>) {
    runApplication<LamaGroupTestApplication>(*args)
}
