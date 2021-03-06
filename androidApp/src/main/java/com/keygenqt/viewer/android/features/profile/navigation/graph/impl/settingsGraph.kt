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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import com.keygenqt.viewer.android.base.AppActions
import com.keygenqt.viewer.android.extensions.composableAnimation
import com.keygenqt.viewer.android.features.profile.navigation.route.ProfileNavRoute
import com.keygenqt.viewer.android.features.profile.ui.actions.SettingsActions
import com.keygenqt.viewer.android.features.profile.ui.screens.settings.SettingsScreen
import com.keygenqt.viewer.android.features.profile.ui.viewModels.SettingsViewModel

/**
 * NavGraph for [SettingsScreen]
 */
@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.settingsGraph(
    appActions: AppActions,
) {
    composableAnimation(ProfileNavRoute.settings.default.route) {
        val viewModel: SettingsViewModel = hiltViewModel()
        SettingsScreen(viewModel = viewModel) { event ->
            when (event) {
                is SettingsActions.UserUpdate -> viewModel.userUpdate(
                    name = event.name,
                    blog = event.blog,
                    twitterUsername = event.twitterUsername,
                    company = event.company,
                    location = event.location,
                    bio = event.bio,
                )
            }
        }
    }
}
