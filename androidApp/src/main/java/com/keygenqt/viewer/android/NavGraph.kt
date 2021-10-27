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
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.insets.statusBarsPadding
import com.keygenqt.githubviewer.Greeting

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph(navController: NavHostController) {

    val navActions = remember(navController) {
        NavActions(navController)
    }

    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        scaffoldState = rememberScaffoldState(),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFFF7F4E7)
                ),
                title = {
                    Text(
                        text = "Compose ❤️ Material You",
                        style = MaterialTheme.typography.titleMedium,
                        color = LocalContentColor.current
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {

                    }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Menu"
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
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {

                    Text(
                        modifier = Modifier,
                        text = Greeting().greeting()
                    )

                    Spacer(modifier = Modifier.size(10.dp))
                    Spacer(modifier = Modifier.height(0.5.dp).fillMaxWidth().background(Color.Gray))
                    Spacer(modifier = Modifier.size(10.dp))

                    Text(
                        modifier = Modifier,
                        text = "Kotlin multiplatform mobile"
                    )

                }
            }
        }
    }
}
