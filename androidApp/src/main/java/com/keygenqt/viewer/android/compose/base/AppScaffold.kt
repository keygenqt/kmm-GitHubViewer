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
package com.keygenqt.viewer.android.compose.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults.enterAlwaysScrollBehavior
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.keygenqt.routing.NavigationDispatcher
import com.keygenqt.viewer.android.compose.overload.AppSwipeRefreshIndicator
import com.keygenqt.viewer.android.extensions.AnimatedNavGraphState
import com.keygenqt.viewer.android.extensions.LaunchedEffectAnimation
import com.keygenqt.viewer.android.menu.MenuBottomBar

/**
 * Main scaffold for app
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    navigationDispatcher: NavigationDispatcher?,
    // top bar
    topBarTitle: String? = null,
    topBarLoading: Boolean = false,
    topBarActions: @Composable ((RowScope) -> Unit)? = null,
    // swipe refresh
    swipeRefreshEnable: Boolean = false,
    swipeRefreshLoading: Boolean = false,
    swipeRefreshAction: () -> Unit = {},
    // content
    content: @Composable () -> Unit
) = AppScaffold(
    backData = null,
    navigationDispatcher = navigationDispatcher,
    topBarTitle = topBarTitle,
    topBarLoading = topBarLoading,
    topBarActions = topBarActions,
    swipeRefreshEnable = swipeRefreshEnable,
    swipeRefreshLoading = swipeRefreshLoading,
    swipeRefreshAction = swipeRefreshAction,
    content = content,
)

/**
 * Main scaffold for app
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> AppScaffold(
    backData: T? = null,
    navigationDispatcher: NavigationDispatcher?,
    // top bar
    topBarTitle: String? = null,
    topBarLoading: Boolean = false,
    topBarActions: @Composable ((RowScope) -> Unit)? = null,
    // swipe refresh
    swipeRefreshEnable: Boolean = false,
    swipeRefreshLoading: Boolean = false,
    swipeRefreshAction: () -> Unit = {},
    // content
    content: @Composable () -> Unit
) {

    // currentDestination
    var currentDestination: NavDestination? by remember {
        mutableStateOf(navigationDispatcher?.currentDestination)
    }

    // update currentDestination
    LaunchedEffectAnimation { state ->
        if (state == AnimatedNavGraphState.END) {
            currentDestination = navigationDispatcher?.currentDestination
        }
    }

    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsWithImePadding(),
        topBar = topBarTitle?.let {
            {
                AppTopBarSmall(
                    backData = backData,
                    title = topBarTitle,
                    navigationDispatcher = navigationDispatcher,
                    scrollBehavior = enterAlwaysScrollBehavior(),
                    loading = topBarLoading,
                    actions = topBarActions
                )
            }
        } ?: {},
        bottomBar = {
            MenuBottomBar(currentDestination)
        }
    ) {
        Box(
            Modifier.padding(it)
        ) {
            if (swipeRefreshEnable) {
                SwipeRefresh(
                    state = rememberSwipeRefreshState(swipeRefreshLoading),
                    onRefresh = swipeRefreshAction,
                    indicator = { st, tr ->
                        AppSwipeRefreshIndicator(st, tr)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    content.invoke()
                }
            } else {
                content.invoke()
            }
        }
    }
}
