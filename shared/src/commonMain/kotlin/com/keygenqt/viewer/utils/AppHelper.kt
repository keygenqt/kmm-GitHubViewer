package com.keygenqt.viewer.utils

import com.keygenqt.viewer.BuildKonfig
import com.keygenqt.viewer.extensions.round
import io.ktor.http.*

object AppHelper {
    /**
     * Generate dynamic link
     */
    private fun getDynamicLink(path: String): String {
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