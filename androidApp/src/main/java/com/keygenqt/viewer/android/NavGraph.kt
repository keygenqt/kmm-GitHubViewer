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

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.enterAlwaysScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.keygenqt.viewer.android.base.AppActions
import com.keygenqt.viewer.android.base.AppViewModel
import com.keygenqt.viewer.android.base.LocalViewModel
import com.keygenqt.viewer.android.base.TopBarTitle.Companion.findTitleByRoute
import com.keygenqt.viewer.android.extensions.AddListenChangeNavigation
import com.keygenqt.viewer.android.features.followers.navigation.graph.followersNavGraph
import com.keygenqt.viewer.android.features.other.navigation.graph.otherNavGraph
import com.keygenqt.viewer.android.features.repos.navigation.graph.reposNavGraph
import com.keygenqt.viewer.android.features.repos.navigation.nav.ReposNav
import com.keygenqt.viewer.android.features.stats.navigation.graph.statsNavGraph
import com.keygenqt.viewer.android.menu.bottomBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph(
    navController: NavHostController,
    appViewModel: AppViewModel = LocalViewModel.current
) {

    // Actions navigation all app
    val appActions = remember(navController) {
        AppActions(navController)
    }

    // Disable scroll if page not have scroll
    val isScroll by appViewModel.isScrollTopBar.collectAsState()

    // Scroll behavior top bar with nestedScrollConnection
    val scrollBehavior = enterAlwaysScrollBehavior { isScroll }

    // Disable scroll if page change
    navController.AddListenChangeNavigation {
        appViewModel.setScrollState(false)
        appViewModel.setTopAppBarTitle(it.route?.findTitleByRoute()?.titleTopBar)
    }

    val topAppBar by appViewModel.topAppBar.collectAsState()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val bottomBar = remember { bottomBar(appActions) }

    val currentRoute = navBackStackEntry?.destination?.route
        ?: ReposNav.navReposMain.reposMainScreen.route

    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        scaffoldState = rememberScaffoldState(),
        topBar = topAppBar?.let { titleId ->
            {
                MediumTopAppBar(
                    scrollBehavior = scrollBehavior,
                    title = {
                        Column {
                            Spacer(modifier = Modifier.size(8.dp))
                            Text(text = stringResource(id = titleId))
                        }
                    }
                )
            }
        } ?: {},
        bottomBar = bottomBar(currentRoute),
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection)
        ) {
            NavHost(
                navController = navController,
                startDestination = currentRoute
            ) {
                otherNavGraph(
                    appActions = appActions
                )
                reposNavGraph(
                    appActions = appActions
                )
                followersNavGraph(
                    appActions = appActions
                )
                statsNavGraph(
                    appActions = appActions
                )
            }
        }
    }
}
