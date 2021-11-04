package com.keygenqt.viewer.android.features.repos.navigation.graph.impl

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.keygenqt.viewer.android.base.AppActions
import com.keygenqt.viewer.android.features.repos.navigation.nav.ReposNav
import com.keygenqt.viewer.android.features.repos.ui.screens.followersMain.ReposMainScreen

/**
 * NavGraph for [ReposMainScreen]
 */
fun NavGraphBuilder.reposMainGraph(
    appActions: AppActions,
) {
    composable(ReposNav.navReposMain.reposMainScreen.route) {
        ReposMainScreen(viewModel = hiltViewModel())
    }
}
