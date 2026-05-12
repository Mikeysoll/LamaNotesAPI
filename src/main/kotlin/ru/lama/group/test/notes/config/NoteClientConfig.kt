package ru.lama.group.test.notes.config

import feign.Logger
import feign.Retryer
import feign.slf4j.Slf4jLogger
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.lama.group.test.notes.client.TestApiClient

@Configuration
open class NoteClientConfig {

    @Value("\${api.base-url}")
    private lateinit var baseUrl: String

    @Value("\${api.project-token}")
    private lateinit var projectToken: String

    @Bean
    open fun testApiClient(): TestApiClient {
        return feign.Feign.builder()
            .client(feign.Client.Default(null, null))
            .requestInterceptor { requestTemplate ->
                requestTemplate.header("X-Project-Token", projectToken)
            }
            .logger(Slf4jLogger())
            .logLevel(Logger.Level.BASIC)
            .retryer(Retryer.NEVER_RETRY)
            .target(TestApiClient::class.java, baseUrl)
    }
}