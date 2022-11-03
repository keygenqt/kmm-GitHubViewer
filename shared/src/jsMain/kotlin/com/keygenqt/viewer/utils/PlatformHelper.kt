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
package com.keygenqt.viewer.utils

import kotlinx.datetime.Instant
import kotlinx.datetime.toJSDate
import kotlin.js.Date

private fun <T> jsonAs(): T = js("({})") as T

private fun option() = jsonAs<Date.LocaleOptions>()

@JsExport
actual object PlatformHelper {

    /**
     * Get timestamp for string
     */
    fun toTimestamp(time: String): Int =
        time.let { (Instant.parse(it).toEpochMilliseconds() / 1000).toInt() }

    /**
     * Format timestamp js
     */
    fun dateFormat(time: Int, format: String): String =
        Instant.fromEpochMilliseconds(time * 1000L)
            .toJSDate()
            .toLocaleDateString(locales = format, options = option().apply {
                year = "numeric"
                month = "long"
                day = "2-digit"
            })
}
