package com.keygenqt.viewer.android.features.followers.navigation.graph.impl

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.keygenqt.viewer.android.base.AppActions
import com.keygenqt.viewer.android.features.followers.navigation.nav.FollowersNav
import com.keygenqt.viewer.android.features.followers.ui.screens.followersMain.FollowersMainScreen

/**
 * NavGraph for [FollowersMainScreen]
 */
fun NavGraphBuilder.followersMainGraph(
    appActions: AppActions,
) {
    composable(FollowersNav.navFollowersMain.followersMainScreen.route) {
        FollowersMainScreen(viewModel = hiltViewModel())
    }
}
