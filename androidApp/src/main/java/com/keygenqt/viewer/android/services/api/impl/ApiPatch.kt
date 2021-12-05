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

import com.keygenqt.viewer.android.data.requests.UserUpdateRequest
import com.keygenqt.viewer.android.data.responses.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PATCH

/**
 * The PATCH method is used to apply partial modifications to a resource.
 */
interface ApiPatch {

    @PATCH("/user")
    suspend fun userUpdate(
        @Body request: UserUpdateRequest
    ): Response<UserResponse>
}
