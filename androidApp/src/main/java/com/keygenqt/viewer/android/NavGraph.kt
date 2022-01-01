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

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.keygenqt.viewer.android.base.AppActions
import com.keygenqt.viewer.android.base.LocalViewModel
import com.keygenqt.viewer.android.extensions.noRippleClickable
import com.keygenqt.viewer.android.features.followers.navigation.graph.followersNavGraph
import com.keygenqt.viewer.android.features.other.navigation.graph.otherNavGraph
import com.keygenqt.viewer.android.features.other.navigation.route.OtherNavRoute
import com.keygenqt.viewer.android.features.profile.navigation.graph.profileNavGraph
import com.keygenqt.viewer.android.features.repos.navigation.graph.reposNavGraph
import com.keygenqt.viewer.android.features.repos.navigation.route.ReposNavRoute
import com.keygenqt.viewer.android.features.stats.navigation.graph.statsNavGraph
import com.keygenqt.viewer.android.menu.MenuBottomBar
import com.keygenqt.viewer.android.menu.MenuTab
import com.keygenqt.viewer.android.utils.AuthUser

@OptIn(ExperimentalAnimationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun NavGraph(
    controller: NavHostController
) {
    val appActions = remember(controller) {
        AppActions(controller)
    }

    val isOnboardingDone by LocalViewModel.current.isOnboardingDone
    val isSplash by LocalViewModel.current.isSplash.collectAsState()

    val startDestination by remember {
        mutableStateOf(
            when {
                !isOnboardingDone -> OtherNavRoute.onboarding.default.route
                AuthUser.isLogin -> ReposNavRoute.repos.default.route
                else -> OtherNavRoute.welcome.default.route
            }
        )
    }

    Box(Modifier.fillMaxSize()) {

        AnimatedNavHost(
            navController = controller,
            startDestination = startDestination
        ) {
            otherNavGraph(appActions)
            reposNavGraph(appActions)
            followersNavGraph(appActions)
            statsNavGraph(appActions)
            profileNavGraph(appActions)
        }

        if (isSplash) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .noRippleClickable {}
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                Image(
                    modifier = Modifier
                        .size(120.dp)
                        .align(Alignment.Center),
                    contentDescription = null,
                    painter = painterResource(id = R.drawable.launcher),
                )
            }
        }
    }

    // navigation bottom bar
    MenuBottomBar.onClick = { tab ->
        when (tab) {
            MenuTab.REPOS -> appActions.toRepos()
            MenuTab.FOLLOWERS -> appActions.toFollowers()
            // MenuTab.STATS -> appActions.toStatsMain() @todo
            MenuTab.PROFILE -> appActions.toProfile()
        }
    }
}
