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
package com.keygenqt.viewer.android.utils

/**
 * Static data app
 */
class StaticData {

    /**
     * Data static user
     */
    object DataUser {
        /**
         * Auth token
         */
        private var LOGIN: String? = null

        /**
         * Get value for [LOGIN]
         */
        val login: String get() = LOGIN!!

        /**
         * Set login
         */
        fun setLogin(login: String) {
            LOGIN = login
        }
    }

    /**
     * Auth tokens user
     */
    object AuthTokens {
        /**
         * Auth token
         */
        private var TOKEN: String? = null

        /**
         * Get value for [TOKEN]
         */
        val token: String? get() = TOKEN

        /**
         * Auth refresh token
         */
        private var TOKEN_REFRESH: String? = null

        /**
         * Get value for [TOKEN_REFRESH]
         */
        val tokenRefresh: String? get() = TOKEN_REFRESH

        /**
         * Set tokens
         */
        fun setTokens(token: String, tokenRefresh: String) {
            TOKEN = token
            TOKEN_REFRESH = tokenRefresh
        }

        /**
         * Clear tokens
         */
        fun clear() {
            TOKEN = null
            TOKEN_REFRESH = null
        }
    }
}
