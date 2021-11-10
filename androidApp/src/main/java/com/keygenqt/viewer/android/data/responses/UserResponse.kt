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
 * @property id user identifier
 * @property avatar_url avatar URL
 * @property name name of the user
 * @property blog blog URL of the user
 * @property twitter_username Twitter username of the user
 * @property company company of the user
 * @property location location of the user
 * @property bio short biography of the user
 */
@Immutable
@Serializable
data class UserResponse(
    val id: Int = 0,
    val avatar_url: String = "",
    val name: String? = null,
    val blog: String? = null,
    val twitter_username: String? = null,
    val company: String? = null,
    val location: String? = null,
    val bio: String? = null,
)
