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

import com.keygenqt.viewer.data.requests.RepoRequest
import com.keygenqt.viewer.data.requests.UserRequest
import com.keygenqt.viewer.data.responses.RepoModel
import com.keygenqt.viewer.data.responses.UserModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class PatchNetwork(val client: HttpClient) {

    /**
     * Update repo data by url
     */
    @Throws(Exception::class)
    suspend fun updateRepo(
        url: String,
        body: RepoRequest
    ): RepoModel {
        return client.patch(url) {
            setBody(body)
        }.body()
    }

    /**
     * Update user data
     */
    @Throws(Exception::class)
    suspend fun updateUser(
        body: UserRequest
    ): UserModel {
        return client.patch("user") {
            setBody(body)
        }.body()
    }
}
