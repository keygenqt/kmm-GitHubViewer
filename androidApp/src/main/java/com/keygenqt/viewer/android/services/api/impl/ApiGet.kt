/*
 * Copyright 2021 Vitaliy Zarubin
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

import com.keygenqt.viewer.android.BuildConfig.GITHUB_CLIENT_ID
import com.keygenqt.viewer.android.data.responses.AuthResponse
import com.keygenqt.viewer.android.data.responses.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url
import java.util.*

/**
 * The GET method requests a representation of the specified resource. Requests using GET should only retrieve data.
 */
interface ApiGet {

    @GET
    suspend fun oauth(
        @Url url: String = "https://github.com/login/oauth/authorize",
        @Query("login") login: String,
        @Query("state") state: String = UUID.randomUUID().toString(),
        @Query("redirect_uri") redirectUri: String = "https://keygenqt.com",
        @Query("allow_signup") allowSignup: Boolean = false,
        @Query("client_id") clientId: String = GITHUB_CLIENT_ID,
    ): Response<AuthResponse>

    @GET("/users/{nickname}")
    suspend fun getUser(@Path("nickname") login: String): Response<UserResponse>
}
