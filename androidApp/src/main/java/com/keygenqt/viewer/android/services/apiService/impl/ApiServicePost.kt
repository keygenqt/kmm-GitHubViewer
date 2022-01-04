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
package com.keygenqt.viewer.android.services.apiService.impl

import com.keygenqt.requests.RequestHandler.executeRequest
import com.keygenqt.requests.ResponseResult
import com.keygenqt.viewer.android.BuildConfig
import com.keygenqt.viewer.android.data.mappers.toModel
import com.keygenqt.viewer.android.data.models.SecurityModel
import com.keygenqt.viewer.android.data.requests.AuthRequest
import com.keygenqt.viewer.android.extensions.delay
import com.keygenqt.viewer.android.extensions.responseCheck
import com.keygenqt.viewer.android.services.api.AppApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * The POST method is used to submit an entity to the specified resource, often causing a change in state or side effects on the server.
 */
interface ApiServicePost {

    val api: AppApi

    /**
     * Query oauth github app
     *
     * @param code from github api for login user
     */
    suspend fun oauthCode(code: String): ResponseResult<SecurityModel> {
        return withContext(Dispatchers.IO) {
            executeRequest(emit = false) {
                api.oauth(
                    request = AuthRequest(
                        code = code,
                        client_secret = BuildConfig.GITHUB_CLIENT_SECRET,
                        client_id = BuildConfig.GITHUB_CLIENT_ID,
                    )
                )
                    .delay(BuildConfig.DEBUG)
                    .responseCheck()
                    .body()
                    ?.toModel()!!
            }
        }
    }
}
