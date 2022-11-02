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
package com.keygenqt.viewer.services.impl

import com.keygenqt.viewer.BuildKonfig
import com.keygenqt.viewer.data.requests.AuthRequest
import com.keygenqt.viewer.data.responses.SecurityModel
import com.keygenqt.viewer.services.AppHttpClient
import com.keygenqt.viewer.utils.AppConstants
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import kotlin.js.Promise

@JsExport
@Suppress("NON_EXPORTABLE_TYPE")
@OptIn(DelicateCoroutinesApi::class)
class PostPromise(private val client: AppHttpClient) {

    /**
     * Get tokens GitHub rest api after oauth by code
     */
    fun oauth(
        code: String
    ): Promise<SecurityModel> {
        return GlobalScope.promise {
            // https://github.com/Rob--W/cors-anywhere
            // It's demo, open reverse proxy will do, but it's not very secure. For production application, the keys should be stored more securely.
            client.httpClient.post("https://cors-anywhere.herokuapp.com/" + AppConstants.LINKS.AUTH_URL) {
                setBody(
                    AuthRequest(
                        code = code,
                        client_secret = BuildKonfig.GITHUB_CLIENT_SECRET,
                        client_id = BuildKonfig.GITHUB_CLIENT_ID,
                    )
                )
            }.body()
        }
    }
}
