package com.keygenqt.viewer.android.features.other.navigation.actions.impl

import androidx.navigation.NavHostController
import com.keygenqt.viewer.android.features.other.navigation.nav.OtherNav
import com.keygenqt.viewer.android.features.other.ui.screens.welcome.WelcomeScreen

/**
 * Actions for [WelcomeScreen]
 */
interface WelcomeActions {

    val controller: NavHostController

    /**
     * To login page
     */
    fun toWelcome() {
        controller.navigate(OtherNav.navWelcome.welcomeScreen.route)
    }
}
