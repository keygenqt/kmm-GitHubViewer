/*
 * Copyright 2022 Vitaliy Zarubin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.keygenqt.viewer.services

import com.keygenqt.viewer.data.responses.ResponseErrorModel
import com.keygenqt.viewer.data.responses.ResponseExceptionModel
import com.keygenqt.viewer.services.impl.GetNetwork
import com.keygenqt.viewer.services.impl.PatchNetwork
import com.keygenqt.viewer.services.impl.PostNetwork
import com.keygenqt.viewer.utils.AppConstants
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class AppHttpClient(
    private var token: String = ""
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

        expectSuccess = false

        HttpResponseValidator {
            validateResponse { response ->
                if (response.status != HttpStatusCode.OK) {
                    val error: ResponseErrorModel = response.body()
                    throw ResponseExceptionModel(
                        code = response.status.value,
                        message = error.message,
                        documentationUrl = error.documentationUrl,
                    )
                }
            }
        }

        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }

        install(DefaultRequest) {
            url(AppConstants.LINKS.API_URL)
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
