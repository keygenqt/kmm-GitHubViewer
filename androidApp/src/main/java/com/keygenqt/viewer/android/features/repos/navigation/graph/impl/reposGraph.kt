/*
 * Copyright 2022 Vitaliy Zarubin
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import com.keygenqt.viewer.android.base.AppActions
import com.keygenqt.viewer.android.extensions.composableAnimation
import com.keygenqt.viewer.android.features.repos.navigation.route.ReposNavRoute
import com.keygenqt.viewer.android.features.repos.ui.actions.ReposActions
import com.keygenqt.viewer.android.features.repos.ui.screens.repos.ReposScreen
import com.keygenqt.viewer.android.features.repos.ui.viewModels.ReposViewModel

/**
 * NavGraph for [ReposScreen]
 */
@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.reposGraph(
    appActions: AppActions,
) {
    composableAnimation(ReposNavRoute.repos.default.route) {
        val viewModel: ReposViewModel = hiltViewModel()
        ReposScreen(viewModel = viewModel) { event ->
            when (event) {
                is ReposActions.SortToggle -> viewModel.sortToggle()
                is ReposActions.ToRepoView -> appActions.toRepo(event.id, event.url)
            }
        }
    }
}
