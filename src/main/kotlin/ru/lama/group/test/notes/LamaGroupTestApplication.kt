package ru.lama.group.test.notes

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class LamaGroupTestApplication

fun main(args: Array<String>) {
    runApplication<LamaGroupTestApplication>(*args)
}
