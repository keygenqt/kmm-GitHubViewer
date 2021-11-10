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
import com.keygenqt.viewer.android.data.requests.UserUpdateRequest
import com.keygenqt.viewer.android.data.responses.UserResponse
import com.keygenqt.viewer.android.extensions.delay
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
     *
     * @param request body request
     *
     * @return response for get userId and token
     */
    suspend fun userUpdate(request: UserUpdateRequest): ResponseResult<UserResponse> {
        return withContext(Dispatchers.IO) {
            LocalTryExecuteWithResponse.executeWithResponse {
                api.userUpdate(request)
                    .delay(BuildConfig.DEBUG)
                    .responseCheck()
                    .body()!!
            }
        }
    }
}
