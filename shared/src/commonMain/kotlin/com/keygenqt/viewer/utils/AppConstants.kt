package com.keygenqt.viewer.utils

object AppConstants {

    val APP = App
    val LINKS = Links
    val DATE_FORMAT = DateFormat
    val LANGUAGE = Language

    object App {
        /**
         * For simulate slow internet
         */
        const val DEBUG_DELAY = 1000L

        /**
         * For debug credential login
         */
        const val DEBUG_CREDENTIAL_LOGIN = "keygenqt"

        /**
         * Limit page size
         */
        const val PAGE_LIMIT = 10
    }

    object Links {
        /**
         * Api url auth
         */
        const val OAUTH_URL = "https://github.com/login/oauth/authorize"
        /**
         * Api url auth
         */
        const val AUTH_URL = "https://github.com/login/oauth/access_token"

        /**
         * Api url
         */
        const val API_URL = "https://api.github.com/"

        /**
         * Deep link path
         */
        const val DYNAMIC_LINK_URL = "https://kmm.keygenqt.com/"
    }

    /**
     * Formats date
     */
    object DateFormat {
        const val SHORT = "dd.MM.yyyy"
    }

    /**
     * Supported languages
     */
    object Language {
        const val SWIFT = "swift"
        const val BASH = "shell"
        const val C = "c"
        const val CPLUSPLUS = "c++"
        const val DART = "dart"
        const val ELIXIR = "elixir"
        const val ERLANG = "erlang"
        const val GROOVY = "groovy"
        const val HASKELL = "haskell"
        const val JAVA = "java"
        const val JAVASCRIPT = "javascript"
        const val KOTLIN = "kotlin"
        const val PHP = "php"
        const val PYTHON = "python"
        const val RUBY = "ruby"
        const val RUST = "rust"
        const val SCALA = "scala"
    }
}