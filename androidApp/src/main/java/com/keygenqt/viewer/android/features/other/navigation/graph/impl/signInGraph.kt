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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navDeepLink
import com.keygenqt.viewer.android.base.AppActions
import com.keygenqt.viewer.android.base.LocalBackPressedDispatcher
import com.keygenqt.viewer.android.extensions.composableAnimation
import com.keygenqt.viewer.android.features.other.navigation.nav.OtherNav
import com.keygenqt.viewer.android.features.other.ui.actions.SignInActions
import com.keygenqt.viewer.android.features.other.ui.screens.signIn.SignInScreen
import com.keygenqt.viewer.android.utils.AppHelper.getDynamicLink
import com.keygenqt.viewer.android.utils.ListenDestination.Companion.clearStack

/**
 * NavGraph for [SignInScreen]
 */
@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.signInGraph(
    appActions: AppActions,
) {
    composableAnimation(
        route = OtherNav.navSignIn.signInScreen.route,
        deepLinks = listOf(
            navDeepLink {
                uriPattern = getDynamicLink("/oauth?code={code}&state={state}")
            }
        )
    ) {
        val backDispatcher: OnBackPressedDispatcher = LocalBackPressedDispatcher.current
        SignInScreen(hiltViewModel()) { event ->
            when (event) {
                is SignInActions.ToStartPage -> appActions.toStartPage(clearStack(backDispatcher))
            }
        }
    }
}
