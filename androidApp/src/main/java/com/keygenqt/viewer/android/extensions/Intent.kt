/**
 * Copyright © 2022 Surf. All rights reserved.
 */
package com.keygenqt.viewer.android.extensions

import android.content.Intent
import android.net.Uri
import timber.log.Timber

/**
 * Get uri deeplink is has
 */
fun Intent.getDeeplink(): Uri? {
    (extras?.getString("click_action") ?: data?.toString())?.let { url ->
        try {
            return Uri.parse(url)
        } catch (_: Exception) {
        }
    }
    return null
}
