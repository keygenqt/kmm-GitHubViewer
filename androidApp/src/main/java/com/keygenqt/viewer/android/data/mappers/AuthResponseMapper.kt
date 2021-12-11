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
package com.keygenqt.viewer.android.data.mappers

import com.keygenqt.viewer.android.data.models.SecurityModel
import com.keygenqt.viewer.android.data.responses.AuthResponse

/**
 * Extension for response [AuthResponse]
 */
fun AuthResponse.toModel(login: String): SecurityModel {
    return SecurityModel(
        login = login,
        accessToken = access_token,
        expiresIn = expires_in.toInt(),
        refreshToken = refresh_token,
        refreshTokenExpiresIn = refresh_token_expires_in.toInt(),
    )
}

/**
 * Extension for list response [AuthResponse]
 */
fun List<AuthResponse>.toModels(login: String): List<SecurityModel> {
    return map { it.toModel(login) }
}
