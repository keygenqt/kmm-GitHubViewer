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

import com.keygenqt.requests.ResponseResult
import com.keygenqt.viewer.android.BuildConfig
import com.keygenqt.viewer.android.base.exceptions.executeRefreshToken
import com.keygenqt.viewer.android.data.mappers.toModel
import com.keygenqt.viewer.android.data.models.RepoModel
import com.keygenqt.viewer.android.data.models.UserModel
import com.keygenqt.viewer.android.data.requests.RepoUpdateRequest
import com.keygenqt.viewer.android.data.requests.UserUpdateRequest
import com.keygenqt.viewer.android.extensions.delay
import com.keygenqt.viewer.android.extensions.responseCheck
import com.keygenqt.viewer.android.services.api.AppApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * The PATCH method is used to apply partial modifications to a resource.
 */
interface ApiServicePatch {

    val api: AppApi

    /**
     * Update user profile
     */
    suspend fun userUpdate(request: UserUpdateRequest): ResponseResult<UserModel> {
        return withContext(Dispatchers.IO) {
            executeRefreshToken(api = api, emit = false) {
                api.userUpdate(request)
                    .delay(BuildConfig.DEBUG)
                    .responseCheck()
                    .body()!!
                    .toModel()
            }
        }
    }

    /**
     * Update repo
     */
    suspend fun repoUpdate(url: String, request: RepoUpdateRequest): ResponseResult<RepoModel> {
        return withContext(Dispatchers.IO) {
            executeRefreshToken(api = api, emit = false) {
                api.repoUpdate(url, request)
                    .delay(BuildConfig.DEBUG)
                    .responseCheck()
                    .body()!!
                    .toModel()
            }
        }
    }
}
