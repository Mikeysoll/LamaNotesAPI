package ru.lama.group.test.notes.config

import feign.Logger
import feign.Retryer
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.lama.group.test.notes.client.UserApiClient
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@Configuration
open class NoteClientConfig {

    companion object {
        private val SSL_CONTEXT = SSLContext.getInstance("TLS").apply {
            this.init(null, arrayOf<TrustManager>(object : X509TrustManager {
                override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
                override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
                override fun getAcceptedIssuers(): Array<X509Certificate> = emptyArray()
            }), SecureRandom())
        }
    }

    @Value("\${api.base-url}")
    private lateinit var baseUrl: String

    @Value("\${api.project-token}")
    private lateinit var projectToken: String

    @Bean
    open fun testApiClient(): UserApiClient {
        return buildClient(UserApiClient::class.java)
    }

    private fun <T> buildClient(tClass: Class<T>): T {
        return feign.Feign.builder()
            .client(feign.Client.Default(SSL_CONTEXT.socketFactory) { _, _ -> true })
            .encoder(JacksonEncoder())
            .decoder(JacksonDecoder())
            .requestInterceptor { requestTemplate ->
                requestTemplate.header("X-Project-Token", projectToken)
                requestTemplate.header("content-type", "application/json")
            }
            .logger(MinimalFeignLogger())
            .logLevel(Logger.Level.FULL)
            .retryer(Retryer.NEVER_RETRY)
            .options(feign.Request.Options(5000, 5000))
            .target(tClass, baseUrl)
    }
}