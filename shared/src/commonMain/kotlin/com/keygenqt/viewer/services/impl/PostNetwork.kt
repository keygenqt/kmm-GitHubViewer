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
import com.keygenqt.viewer.utils.AppConstants
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class PostNetwork(val client: HttpClient) {

    /**
     * Get tokens GitHub rest api after oauth by code
     */
    @Throws(Exception::class)
    suspend fun oauth(
        code: String
    ): SecurityModel {
        return client.post(AppConstants.Links.AUTH_URL) {
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
