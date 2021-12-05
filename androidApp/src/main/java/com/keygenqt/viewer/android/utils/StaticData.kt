/**
 * Copyright © 2021 Surf. All rights reserved.
 */
package com.keygenqt.viewer.android.utils

/**
 * Static data app
 */
class StaticData {

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
