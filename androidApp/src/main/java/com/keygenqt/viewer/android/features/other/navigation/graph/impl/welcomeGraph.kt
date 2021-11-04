package com.keygenqt.viewer.android.features.other.navigation.graph.impl

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.keygenqt.viewer.android.base.AppActions
import com.keygenqt.viewer.android.features.other.navigation.nav.OtherNav
import com.keygenqt.viewer.android.features.other.ui.actions.WelcomeActions
import com.keygenqt.viewer.android.features.other.ui.screens.welcome.WelcomeScreen

/**
 * NavGraph for [WelcomeScreen]
 */
fun NavGraphBuilder.welcomeGraph(
    appActions: AppActions,
) {
    composable(OtherNav.navWelcome.welcomeScreen.route) {
        WelcomeScreen(viewModel = hiltViewModel()) { event ->
            when (event) {
                is WelcomeActions.ToLogin -> {

                }
            }
        }
    }
}
