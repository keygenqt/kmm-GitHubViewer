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
package com.keygenqt.viewer.android.services.api.impl

import com.keygenqt.viewer.android.data.requests.AuthRequest
import com.keygenqt.viewer.android.data.responses.AuthResponse
import com.keygenqt.viewer.utils.AppConstants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Url

/**
 * The POST method is used to submit an entity to the specified resource, often causing a change in state or side effects on the server.
 */
interface ApiPost {
    @POST
    suspend fun oauth(
        @Url url: String = AppConstants.AUTH_URL,
        @Body request: AuthRequest
    ): Response<AuthResponse>
}
