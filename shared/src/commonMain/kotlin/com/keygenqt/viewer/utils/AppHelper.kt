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

import com.keygenqt.viewer.BuildKonfig
import com.keygenqt.viewer.extensions.round
import io.ktor.http.*

object AppHelper {
    /**
     * Generate dynamic link
     */
    fun getDynamicLink(path: String): String {
        return "https://${BuildKonfig.dynamicLinksHost}/$path"
    }

    /**
     * Generate oauth link
     */
    fun getOauthLink(
        login: String,
        state: String
    ): String {
        return URLBuilder(AppConstants.Links.OAUTH_URL).apply {
            with(parameters) {
                append("login", login)
                append("state", state)
                append("redirect_uri", getDynamicLink("oauth"))
                append("allow_signup", false.toString())
                append("client_id", BuildKonfig.GITHUB_CLIENT_ID)
            }
        }.build().toString()
    }

    /**
     * Bites to human string
     */
    fun humanReadableByte(bytes: Long) = when {
        bytes == Long.MIN_VALUE || bytes < 0 -> "N/A"
        bytes < 1024L -> "$bytes B"
        bytes <= 0xfffccccccccccccL shr 40 -> "${(bytes.toDouble() / (0x1 shl 10)).round()} KiB"
        bytes <= 0xfffccccccccccccL shr 30 -> "${(bytes.toDouble() / (0x1 shl 20)).round()} MiB"
        bytes <= 0xfffccccccccccccL shr 30 -> "${(bytes.toDouble() / (0x1 shl 20)).round()} MiB"
        bytes <= 0xfffccccccccccccL shr 20 -> "${(bytes.toDouble() / (0x1 shl 30)).round()} GiB"
        else -> "Too big"
    }
}
