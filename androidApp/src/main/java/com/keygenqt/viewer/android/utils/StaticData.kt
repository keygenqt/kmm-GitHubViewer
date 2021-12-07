/**
 * Copyright © 2021 Surf. All rights reserved.
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
