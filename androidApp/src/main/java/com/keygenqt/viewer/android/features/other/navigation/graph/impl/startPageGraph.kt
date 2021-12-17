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
package com.keygenqt.viewer.android.features.other.navigation.graph.impl

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.keygenqt.viewer.android.base.AppActions
import com.keygenqt.viewer.android.base.LocalBackPressedDispatcher
import com.keygenqt.viewer.android.base.LocalViewModel
import com.keygenqt.viewer.android.features.other.navigation.nav.OtherNav
import com.keygenqt.viewer.android.features.other.ui.actions.StartPageActions
import com.keygenqt.viewer.android.features.other.ui.screens.startPage.StartPageScreen
import com.keygenqt.viewer.android.features.other.ui.screens.welcome.WelcomeScreen
import com.keygenqt.viewer.android.features.other.ui.viewModels.StartPageViewModel
import com.keygenqt.viewer.android.utils.ListenDestination

/**
 * NavGraph for [WelcomeScreen]
 */
@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.startPageGraph(
    appActions: AppActions,
) {
    composable(
        route = OtherNav.navStartPage.startPageScreen.route,
        enterTransition = {
            slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(700))
        },
        exitTransition = {
            slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(700))
        },
        popEnterTransition = {
            slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(700))
        },
        popExitTransition = {
            slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(700))
        }
    ) {
        val backDispatcher: OnBackPressedDispatcher = LocalBackPressedDispatcher.current
        val localViewModel = LocalViewModel.current
        val viewModel: StartPageViewModel = hiltViewModel()
        StartPageScreen(viewModel = viewModel) { event ->
            when (event) {
                is StartPageActions.StartLoadingData -> viewModel.startLoading()
                is StartPageActions.ToSignIn -> {
                    localViewModel.logout {
                        appActions.toSignIn(
                            ListenDestination.clearStack(
                                backDispatcher
                            )
                        )
                    }
                }
                is StartPageActions.ToRepos -> appActions.toReposMain(
                    ListenDestination.clearStack(
                        backDispatcher
                    )
                )
            }
        }
    }
}
