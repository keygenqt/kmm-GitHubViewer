/*
 * Copyright 2021 Vitaliy Zarubin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
