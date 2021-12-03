/**
 * Copyright © 2021 Surf. All rights reserved.
 */
package com.keygenqt.viewer.android.extensions

import androidx.navigation.NavBackStackEntry

/**
 * Get string argument
 *
 * @author Vitaliy Zarubin
 */
fun NavBackStackEntry.getString(key: String): String? {
    val value = arguments?.getString(key)
    return if (value != "{$key}") value else null
}