package com.keygenqt.viewer.android.features.stats.navigation.graph.impl

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.keygenqt.viewer.android.base.AppActions
import com.keygenqt.viewer.android.features.stats.navigation.nav.StatsNav
import com.keygenqt.viewer.android.features.stats.ui.screens.statsMain.StatsMainScreen

/**
 * NavGraph for [StatsMainScreen]
 */
fun NavGraphBuilder.statsMainGraph(
    appActions: AppActions,
) {
    composable(StatsNav.navStatsMain.statsMainScreen.route) {
        StatsMainScreen(viewModel = hiltViewModel())
    }
}
