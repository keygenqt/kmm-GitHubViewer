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
package com.keygenqt.viewer.android.utils

import android.net.Uri
import com.keygenqt.viewer.android.BuildConfig
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*

object AppHelper {

    /**
     * Check string is not json
     */
    fun isValidJson(test: String?): Boolean {
        if (test == null) {
            return false
        }
        try {
            JSONObject(test)
        } catch (ex: JSONException) {
            try {
                JSONArray(test)
            } catch (ex1: JSONException) {
                return false
            }
        }
        return true
    }

    /**
     * Generate dynamic link
     */
    fun getDynamicLink(path: String): String {
        return "https://${BuildConfig.dynamicLinksHost}$path"
    }

    /**
     * Generate oauth link
     */
    fun getOauthLink(login: String): String {
        return Uri.Builder().apply {
            scheme("https")
            authority("github.com")
            appendPath("login")
            appendPath("oauth")
            appendPath("authorize")
            appendQueryParameter("login", login)
            appendQueryParameter("state", UUID.randomUUID().toString())
            appendQueryParameter("redirect_uri", getDynamicLink("/oauth"))
            appendQueryParameter("allow_signup", false.toString())
            appendQueryParameter("client_id", BuildConfig.GITHUB_CLIENT_ID)
            build()
        }.toString()
    }
}
