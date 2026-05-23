plugins {
    kotlin("jvm") version "2.3.0"
    id("org.springframework.boot") version "3.4.5"
    id("io.spring.dependency-management") version "1.1.7"
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
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // ===== REST ASSURED =====
    testImplementation("io.rest-assured:rest-assured:5.5.0")
    testImplementation("io.rest-assured:json-path:5.5.0")

    // ===== JSON SERIALIZATION (КРИТИЧНО) =====
    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.17.2")
    testImplementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.2")

    // ===== TESTS =====
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation(kotlin("test"))

    // ===== ALLURE =====
    testImplementation("io.qameta.allure:allure-junit5:2.34.0")
}

tasks.test {
    useJUnitPlatform()
}