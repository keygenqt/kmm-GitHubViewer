/**
 * Copyright © 2021 Surf. All rights reserved.
 */
package com.keygenqt.viewer.android.base

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * [AppViewModel] Can be used in CompositionLocalProvider to provide values.
 *
 * @author Vitaliy Zarubin
 */
val LocalViewModel = staticCompositionLocalOf<AppViewModel> {
    error("No AppViewModel found!")
}

/**
 * [OnBackPressedDispatcher] Can be used in CompositionLocalProvider to provide values.
 *
 * @author Vitaliy Zarubin
 */
val LocalBackPressedDispatcher = staticCompositionLocalOf<OnBackPressedDispatcher> {
    error("No Back Dispatcher provided")
}
