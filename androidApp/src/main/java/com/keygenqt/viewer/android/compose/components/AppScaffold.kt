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
package com.keygenqt.viewer.android.compose.components

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.keygenqt.viewer.android.base.LocalBackPressedDispatcher
import com.keygenqt.viewer.android.compose.components.lottie.LoadingBlockAnimation
import com.keygenqt.viewer.android.menu.MenuBottomBar
import com.keygenqt.viewer.android.utils.ListenDestination

/**
 * Main scaffold for app
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    // top bar
    topBarTitle: String? = null,
    topBarLoading: Boolean = false,
    topBarActions: @Composable ((RowScope) -> Unit)? = null,
    // swipe refresh
    swipeRefreshEnable: Boolean = false,
    swipeRefreshLoading: Boolean = false,
    swipeRefreshAction: () -> Unit = {},
    // for nested scroll state
    scrollState: ScrollState? = null,
    refreshState: SwipeRefreshState? = null,
    // locals
    backDispatcher: OnBackPressedDispatcher = LocalBackPressedDispatcher.current,
    // content
    content: @Composable () -> Unit
) {
    // Scroll behavior top bar with nestedScrollConnection
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior {
        refreshState?.isSwipeInProgress == true || scrollState?.value ?: 0 > 0
    }

    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsWithImePadding(),
        topBar = topBarTitle?.let {
            {
                MediumTopAppBar(
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    ),
                    scrollBehavior = scrollBehavior,
                    navigationIcon = if (backDispatcher.hasEnabledCallbacks()) {
                        {
                            IconButton(onClick = {
                                backDispatcher.onBackPressed()
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Back",
                                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            }
                        }
                    } else {
                        {}
                    },
                    title = {
                        Column {
                            Spacer(modifier = Modifier.size(8.dp))
                            Text(text = topBarTitle)
                        }
                    },
                    actions = {
                        if (topBarLoading) {
                            // show loading indicator
                            LoadingBlockAnimation()
                        } else {
                            // custom actions
                            topBarActions?.invoke(this)
                        }
                    }
                )
            }
        } ?: {},
        bottomBar = {
            MenuBottomBar(ListenDestination.getActionDestination())
        }
    ) {
        Box(
            Modifier
                .nestedScroll(scrollBehavior.nestedScrollConnection)
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
