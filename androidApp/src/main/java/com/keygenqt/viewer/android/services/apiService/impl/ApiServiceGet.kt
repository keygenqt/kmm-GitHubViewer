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
package com.keygenqt.viewer.android.services.apiService.impl

import com.keygenqt.response.LocalTryExecuteWithResponse
import com.keygenqt.response.ResponseResult
import com.keygenqt.response.extensions.responseCheck
import com.keygenqt.viewer.android.BuildConfig
import com.keygenqt.viewer.android.data.mappers.toModel
import com.keygenqt.viewer.android.data.models.UserModel
import com.keygenqt.viewer.android.extensions.delay
import com.keygenqt.viewer.android.services.api.AppApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * The GET method requests a representation of the specified resource. Requests using GET should only retrieve data.
 */
interface ApiServiceGet {

    val api: AppApi

    /**
     * Query login user with callback if success. For example use random query with error response.
     *
     * @param nickname login user
     *
     * @return ResponseResult with user
     */
    suspend fun getUser(nickname: String): ResponseResult<UserModel> {
        return withContext(Dispatchers.IO) {
            LocalTryExecuteWithResponse.executeWithResponse {
                api.getUser(nickname)
                    .delay(BuildConfig.DEBUG)
                    .responseCheck()
                    .body()!!
                    .toModel()
            }
        }
    }
}
