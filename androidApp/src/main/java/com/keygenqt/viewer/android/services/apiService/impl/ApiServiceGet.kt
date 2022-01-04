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

import androidx.annotation.IntRange
import com.keygenqt.requests.ResponseResult
import com.keygenqt.viewer.android.BuildConfig
import com.keygenqt.viewer.android.base.exceptions.executeRefreshToken
import com.keygenqt.viewer.android.data.mappers.toModel
import com.keygenqt.viewer.android.data.mappers.toModels
import com.keygenqt.viewer.android.data.models.FollowerModel
import com.keygenqt.viewer.android.data.models.RepoModel
import com.keygenqt.viewer.android.data.models.UserModel
import com.keygenqt.viewer.android.extensions.delay
import com.keygenqt.viewer.android.extensions.responseCheck
import com.keygenqt.viewer.android.services.api.AppApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * The GET method requests a representation of the specified resource. Requests using GET should only retrieve data.
 */
interface ApiServiceGet {

    val api: AppApi

    /**
     * Get user data
     */
    suspend fun getUser(): ResponseResult<UserModel> {
        return withContext(Dispatchers.IO) {
            executeRefreshToken(api) {
                api.getUser()
                    .delay(BuildConfig.DEBUG)
                    .responseCheck()
                    .body()!!
                    .toModel()
            }
        }
    }

    /**
     * Get repo data
     */
    suspend fun getRepo(url: String): ResponseResult<RepoModel> {
        return withContext(Dispatchers.IO) {
            executeRefreshToken(api) {
                api.getRepo(url)
                    .delay(BuildConfig.DEBUG)
                    .responseCheck()
                    .body()!!
                    .toModel()
            }
        }
    }

    /**
     * Get user repos
     */
    suspend fun getUserRepos(
        @IntRange(from = 1) page: Int = 1,
        isSortDesc: Boolean = false,
    ): ResponseResult<List<RepoModel>> {
        return withContext(Dispatchers.IO) {
            executeRefreshToken(api) {
                api.getUserRepos(
                    page = page,
                    direction = if (isSortDesc) "desc" else "asc"
                )
                    .delay(BuildConfig.DEBUG)
                    .responseCheck()
                    .body()!!
                    .toModels()
            }
        }
    }

    /**
     * Get user followers
     */
    suspend fun getUserFollowers(
        @IntRange(from = 1) page: Int = 1
    ): ResponseResult<List<FollowerModel>> {
        return withContext(Dispatchers.IO) {
            executeRefreshToken(api) {
                api.getUserFollowers(page = page)
                    .delay(BuildConfig.DEBUG)
                    .responseCheck()
                    .body()!!
                    .toModels()
            }
        }
    }
}
