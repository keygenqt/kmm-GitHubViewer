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
package com.keygenqt.viewer.android

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.keygenqt.viewer.android.base.AppActions
import com.keygenqt.viewer.android.base.AppViewModel
import com.keygenqt.viewer.android.base.LocalViewModel
import com.keygenqt.viewer.android.features.followers.navigation.graph.followersNavGraph
import com.keygenqt.viewer.android.features.other.navigation.graph.otherNavGraph
import com.keygenqt.viewer.android.features.other.navigation.nav.OtherNav
import com.keygenqt.viewer.android.features.profile.navigation.graph.profileNavGraph
import com.keygenqt.viewer.android.features.repos.navigation.graph.reposNavGraph
import com.keygenqt.viewer.android.features.stats.navigation.graph.statsNavGraph
import com.keygenqt.viewer.android.utils.ConstantsApp.START_DESTINATION
import com.keygenqt.viewer.android.utils.ListenDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph(
    navController: NavHostController,
    appViewModel: AppViewModel = LocalViewModel.current,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
) {
    // Actions navigation all app
    val appActions = remember(navController) {
        AppActions(navController)
    }

    // set actions for AppScaffold
    appViewModel.setBottomBarActions(appActions)

    ListenDestination.Init(navController)

    val isLogin by appViewModel.isLogin.collectAsState(null)

    isLogin?.let {
        LaunchedEffect(lifecycleOwner.lifecycle.currentState == Lifecycle.State.CREATED) {
            if (!it && ListenDestination.getActionDestination()?.route != OtherNav.navSignIn.signInScreen.route) {
                appActions.toWelcome()
            }
            appViewModel.disableSplash()
        }
    }

    NavHost(
        navController = navController,
        startDestination = START_DESTINATION
    ) {
        otherNavGraph(appActions)
        reposNavGraph(appActions)
        followersNavGraph(appActions)
        statsNavGraph(appActions)
        profileNavGraph(appActions)
    }
}
