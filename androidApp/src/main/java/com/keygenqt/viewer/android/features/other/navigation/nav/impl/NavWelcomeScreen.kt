/**
 * Copyright © 2021 Surf. All rights reserved.
 */
package com.keygenqt.viewer.android.features.other.navigation.nav.impl

import com.keygenqt.routing.NavScreen
import com.keygenqt.viewer.android.features.other.ui.screens.welcome.WelcomeScreen

/**
 * Routing for [WelcomeScreen]
 */
object NavWelcomeScreen {
    val welcomeScreen = object : NavScreen {
        override val route: String = "NavWelcomeScreen"
    }
}
