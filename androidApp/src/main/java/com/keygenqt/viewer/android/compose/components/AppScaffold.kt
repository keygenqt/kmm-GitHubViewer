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
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import com.keygenqt.viewer.android.base.AppViewModel
import com.keygenqt.viewer.android.base.LocalBackPressedDispatcher
import com.keygenqt.viewer.android.base.LocalViewModel

/**
 * Main scaffold for app
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    title: String? = null,
    loading: Boolean = false,
    scrollState: ScrollState? = null,
    appViewModel: AppViewModel = LocalViewModel.current,
    backDispatcher: OnBackPressedDispatcher = LocalBackPressedDispatcher.current,
    actions: @Composable ((RowScope) -> Unit)? = null,
    content: @Composable () -> Unit
) {
    // Scroll behavior top bar with nestedScrollConnection
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior {
        scrollState?.value ?: 0 > 0
    }

    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsWithImePadding(),
        scaffoldState = rememberScaffoldState(),
        topBar = title?.let {
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
                            Text(text = title)
                        }
                    },
                    actions = {
                        // custom actions
                        actions?.invoke(this)
                        // show loading indicator
                        if (loading) {
                            IconButton(
                                enabled = false,
                                onClick = {}
                            ) {
                                CircularProgressIndicator(
                                    strokeWidth = 2.dp,
                                    modifier = Modifier.size(20.dp),
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            }
                        }
                    }
                )
            }
        } ?: {},
        bottomBar = appViewModel.getBottomBar() ?: {}
    ) {
        Box(
            Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        ) {
            content.invoke()
        }
    }
}
