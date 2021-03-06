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
package com.keygenqt.viewer.android.data.requests

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

/**
 * Refresh token request
 *
 * @property refresh_token The token generated when the GitHub App owner enables expiring tokens and issues a new user access token.
 * @property grant_type Value must be refresh_token (required by the OAuth specification).
 * @property client_secret The client ID for your GitHub App.
 * @property client_id The client secret for your GitHub App.
 */
@Immutable
@Serializable
data class RefreshTokenRequest(
    val refresh_token: String,
    val grant_type: String,
    val client_secret: String,
    val client_id: String,
)
