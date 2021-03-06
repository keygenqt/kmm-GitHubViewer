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
package com.keygenqt.viewer.android.base.exceptions

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

/**
 * Error server response
 */
@Immutable
@Serializable
data class DataException(
    val code: Int = 0,
    val error: String = "",
    val error_description: String = "",
    val error_uri: String = "",
    override val message: String = "Something wrong",
    val documentation_url: String = "",
) : RuntimeException()
