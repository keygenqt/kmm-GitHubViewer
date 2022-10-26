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
package com.keygenqt.viewer.android.features.profile.navigation.graph.impl

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import com.keygenqt.routing.NavigationDispatcher
import com.keygenqt.viewer.android.base.AppActions
import com.keygenqt.viewer.android.base.LocalNavigationDispatcher
import com.keygenqt.viewer.android.extensions.composableAnimation
import com.keygenqt.viewer.android.features.profile.navigation.route.ProfileNavRoute
import com.keygenqt.viewer.android.features.profile.ui.actions.ProfileActions
import com.keygenqt.viewer.android.features.profile.ui.screens.profile.ProfileScreen
import com.keygenqt.viewer.android.features.profile.ui.viewModels.ProfileViewModel
import com.keygenqt.viewer.android.utils.AuthUser
import kotlinx.coroutines.launch

/**
 * NavGraph for [ProfileScreen]
 */
@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.profileGraph(
    appActions: AppActions,
) {
    composableAnimation(ProfileNavRoute.profile.default.route) {

        val scope = rememberCoroutineScope()
        val viewModel: ProfileViewModel = hiltViewModel()
        val navDispatcher: NavigationDispatcher = LocalNavigationDispatcher.current

        ProfileScreen(viewModel = viewModel) { event ->
            when (event) {
                is ProfileActions.ToSettings -> appActions.toSettings()
                is ProfileActions.ActionUpdateUser -> viewModel.updateProfile()
                is ProfileActions.ActionLogout -> scope.launch {
                    AuthUser.logout()
                    viewModel.clearStorage()
                    navDispatcher.toRoutePopStack(appActions::toWelcome, appActions::toSignIn)
                }
            }
        }
    }
}
