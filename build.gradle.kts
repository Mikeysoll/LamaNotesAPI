plugins {
    kotlin("jvm") version "2.3.0"
}

group = "ru.lama.group.test"
version = "1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation(kotlin("test"))
    testImplementation("org.assertj:assertj-core:4.0.0-M1")

    // ===== REST ASSURED =====
    testImplementation("io.rest-assured:rest-assured:5.5.0")
    testImplementation("io.rest-assured:json-path:5.5.0")

    // ===== JSON SERIALIZATION =====
    testImplementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.2")

    // ===== ALLURE =====
    testImplementation("io.qameta.allure:allure-junit5:2.34.0")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}