package com.keygenqt.viewer.services

import com.keygenqt.viewer.services.impl.GetNetwork
import com.keygenqt.viewer.services.impl.PatchNetwork
import com.keygenqt.viewer.services.impl.PostNetwork
import com.keygenqt.viewer.utils.AppConstants
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class AppHttpClient(
    private var token: String
) {

    fun setToken(token: String) {
        this.token = token
    }

    fun clearToken() {
        this.token = ""
    }

    val get by lazy { GetNetwork(httpClient) }
    val post by lazy { PostNetwork(httpClient) }
    val patch by lazy { PatchNetwork(httpClient) }

    private val json = Json {
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true
    }

    val httpClient = HttpClient {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
        install(DefaultRequest) {
            url(AppConstants.API_URL)
            if (token.isNotBlank()) {
                header(HttpHeaders.Authorization, "token $token")
            }
            header(HttpHeaders.Accept, "application/vnd.github.v3+json")
            contentType(ContentType.Application.Json)
        }
        install(ContentNegotiation) {
            json(json)
        }
    }
}