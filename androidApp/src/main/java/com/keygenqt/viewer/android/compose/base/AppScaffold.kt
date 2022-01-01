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
package com.keygenqt.viewer.android.compose.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults.enterAlwaysScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Modifier.Companion.then
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.keygenqt.viewer.android.base.LocalNavigationDispatcher
import com.keygenqt.viewer.android.compose.overload.AppSwipeRefreshIndicator
import com.keygenqt.viewer.android.menu.MenuBottomBar

/**
 * Main scaffold for app
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    // top bar
    topBarTitle: String? = null,
    topBarLoading: Boolean = false,
    topBarIsSmall: Boolean = false,
    topBarActions: @Composable ((RowScope) -> Unit)? = null,
    // swipe refresh
    swipeRefreshEnable: Boolean = false,
    swipeRefreshLoading: Boolean = false,
    swipeRefreshAction: () -> Unit = {},
    // content
    content: @Composable () -> Unit
) {
    val navigationDispatcher = LocalNavigationDispatcher.current
    val scrollBehavior = enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsWithImePadding(),
        topBar = topBarTitle?.let {
            {
                if (topBarIsSmall) {
                    AppTopBarSmall(
                        title = topBarTitle,
                        scrollBehavior = scrollBehavior,
                        loading = topBarLoading,
                        actions = topBarActions
                    )
                } else {
                    AppTopBarMedium(
                        title = topBarTitle,
                        scrollBehavior = scrollBehavior,
                        loading = topBarLoading,
                        actions = topBarActions
                    )
                }
            }
        } ?: {},
        bottomBar = {
            MenuBottomBar(navigationDispatcher.currentDestination)
        }
    ) {
        Box(
            Modifier
                .let { modifier ->
                    if (topBarIsSmall) {
                        modifier
                    } else {
                        then(modifier.nestedScroll(scrollBehavior.nestedScrollConnection))
                    }
                }
                .padding(it)
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
