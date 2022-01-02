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

import androidx.annotation.IntRange
import com.keygenqt.viewer.android.data.responses.FollowerResponse
import com.keygenqt.viewer.android.data.responses.RepoResponse
import com.keygenqt.viewer.android.data.responses.UserResponse
import com.keygenqt.viewer.android.utils.ConstantsPaging.PAGE_LIMIT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * The GET method requests a representation of the specified resource. Requests using GET should only retrieve data.
 */
interface ApiGet {

    @GET("/user1")
    suspend fun getUser(): Response<UserResponse>

    @GET
    suspend fun getRepo(
        @Url url: String,
    ): Response<RepoResponse>

    @GET("user/repos")
    suspend fun getUserRepos(
        @Query("page") @IntRange(from = 1) page: Int = 1,
        @Query("per_page") @IntRange(from = 1) perPage: Int = PAGE_LIMIT,
        @Query("type") type: String = "owner",
        @Query("sort") sort: String = "created",
        @Query("direction") direction: String = "asc"
    ): Response<List<RepoResponse>>

    @GET("user/followers")
    suspend fun getUserFollowers(
        @Query("page") @IntRange(from = 1) page: Int = 1,
        @Query("per_page") @IntRange(from = 1) perPage: Int = PAGE_LIMIT,
    ): Response<List<FollowerResponse>>
}
