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
package com.keygenqt.viewer.android.data.models

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.keygenqt.viewer.android.interfaces.IModel

/**
 * User for all app
 *
 * @property id key from api
 * @property avatarUrl avatar URL
 * @property name name of the user
 * @property blog blog URL of the user
 * @property twitterUsername Twitter username of the user
 * @property company company of the user
 * @property location location of the user
 * @property bio short biography of the user
 */
@Entity
@Immutable
data class UserModel(
    @PrimaryKey override val id: String,
    val avatarUrl: String,
    val name: String,
    val blog: String,
    val twitterUsername: String,
    val company: String,
    val location: String,
    val bio: String,
) : IModel
