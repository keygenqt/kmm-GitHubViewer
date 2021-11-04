package com.keygenqt.viewer.android.features.other.ui.actions

import com.keygenqt.viewer.android.features.other.ui.screens.welcome.WelcomeScreen

/**
 * Actions sealed class for screen [WelcomeScreen]
 */
sealed class WelcomeActions {

    /**
     * Open login page
     */
    object ToLogin : WelcomeActions()
}
