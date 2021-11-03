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

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.enterAlwaysScrollBehavior
import androidx.compose.material3.TopAppBarDefaults.pinnedScrollBehavior
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.rememberImeNestedScrollConnection
import com.google.accompanist.insets.statusBarsPadding
import com.keygenqt.githubviewer.Greeting
import timber.log.Timber
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph(navController: NavHostController) {

    val navActions = remember(navController) {
        NavActions(navController)
    }

    val scrollState = rememberScrollState()

    val scrollBehavior = enterAlwaysScrollBehavior {
        scrollState.value > 1
    }

    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        scaffoldState = rememberScaffoldState(),
        topBar = {
            MediumTopAppBar(
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
                title = {
                    Text(text = "Compose ❤️ Material You")
                },
                navigationIcon = {
                    IconButton(onClick = {

                    }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Menu",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            )
        },
    ) {
        NavHost(
            navController = navController,
            startDestination = NavRoute.Welcome.route
        ) {
            composable(NavRoute.Welcome.route) {
                Box(
                    Modifier
                        .nestedScroll(scrollBehavior.nestedScrollConnection)
                        .navigationBarsWithImePadding()
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .verticalScroll(scrollState)
                            .background(MaterialTheme.colorScheme.background)
                    ) {
                        Column(Modifier.padding(20.dp)) {
                            Text(
                                modifier = Modifier,
                                text = Greeting().greeting()
                            )

                            Spacer(modifier = Modifier.size(20.dp))

                            Text(
                                "State: ${scrollState.value}"
                            )

                            Spacer(modifier = Modifier.size(20.dp))

                            Spacer(
                                modifier = Modifier
                                    .height(0.5.dp)
                                    .fillMaxWidth()
                                    .background(Color.Gray)
                            )

                            Spacer(modifier = Modifier.size(20.dp))

                            Text(
                                modifier = Modifier,
                                text = "Kotlin multiplatform mobile"
                            )

                            Spacer(modifier = Modifier.size(1000.dp))

                            Text(
                                modifier = Modifier,
                                text = "Scroll bottom"
                            )
                        }
                    }
                }
            }
        }
    }
}
