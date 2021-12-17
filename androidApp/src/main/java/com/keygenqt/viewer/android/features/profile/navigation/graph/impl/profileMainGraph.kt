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
package com.keygenqt.viewer.android.features.profile.navigation.graph.impl

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.keygenqt.viewer.android.base.AppActions
import com.keygenqt.viewer.android.base.LocalBackPressedDispatcher
import com.keygenqt.viewer.android.base.LocalViewModel
import com.keygenqt.viewer.android.features.profile.navigation.nav.ProfileNav
import com.keygenqt.viewer.android.features.profile.ui.actions.ProfileMainActions
import com.keygenqt.viewer.android.features.profile.ui.screens.profileMain.ProfileMainScreen
import com.keygenqt.viewer.android.features.profile.ui.viewModels.ProfileViewModel
import com.keygenqt.viewer.android.utils.ListenDestination

/**
 * NavGraph for [ProfileMainScreen]
 */
@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.profileMainGraph(
    appActions: AppActions,
) {
    composable(ProfileNav.navProfileMain.profileMainScreen.route) {
        val backDispatcher: OnBackPressedDispatcher = LocalBackPressedDispatcher.current
        val viewModel: ProfileViewModel = hiltViewModel()
        val appViewModel = LocalViewModel.current
        ProfileMainScreen(viewModel = viewModel) { event ->
            when (event) {
                is ProfileMainActions.ToSettings -> appActions.toSettings()
                is ProfileMainActions.Logout -> appViewModel.logout {
                    appActions.toSignIn(ListenDestination.clearStack(backDispatcher))
                }
            }
        }
    }
}
