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
package com.keygenqt.viewer.android.features.repos.navigation.graph.impl

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.keygenqt.viewer.android.base.AppActions
import com.keygenqt.viewer.android.features.repos.navigation.nav.ReposNav
import com.keygenqt.viewer.android.features.repos.ui.actions.ReposListActions
import com.keygenqt.viewer.android.features.repos.ui.screens.reposList.ReposListScreen
import com.keygenqt.viewer.android.features.repos.ui.viewModels.ReposListViewModel

/**
 * NavGraph for [ReposListScreen]
 */
@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.reposMainGraph(
    appActions: AppActions,
) {
    composable(
        route = ReposNav.navReposList.reposMainScreen.route,
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
        },
    ) {
        val viewModel: ReposListViewModel = hiltViewModel()
        ReposListScreen(viewModel = viewModel) { event ->
            when (event) {
                is ReposListActions.SortToggle -> viewModel.sortToggle()
                is ReposListActions.ToRepoView -> appActions.toRepo(event.id)
            }
        }
    }
}
