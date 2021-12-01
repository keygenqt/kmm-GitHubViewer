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
package com.keygenqt.viewer.android.data.responses

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

/**
 * Response user
 *
 * @property client_id The client ID you received from GitHub for your OAuth App.
 * @property client_secret The client secret you received from GitHub for your OAuth App.
 * @property code The code you received as a response to Step 1.
 * @property redirect_uri The URL in your application where users are sent after authorization.
 */
@Immutable
@Serializable
data class AuthResponse(
    val client_id: String,
    val client_secret: String,
    val code: String,
    val redirect_uri: String? = null,
)
