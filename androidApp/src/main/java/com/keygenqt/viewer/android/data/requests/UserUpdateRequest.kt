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
package com.keygenqt.viewer.android.data.requests

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

/**
 * @Body request update user data
 *
 * @property name The new name of the user.
 * @property blog The new blog URL of the user.
 * @property twitter_username The new Twitter username of the user.
 * @property company The new company of the user.
 * @property location The new location of the user.
 * @property bio The new short biography of the user.
 */
@Immutable
@Serializable
data class UserUpdateRequest(
    val name: String,
    val blog: String,
    val twitter_username: String?,
    val company: String,
    val location: String,
    val bio: String,
)
