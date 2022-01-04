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
package com.keygenqt.viewer.android.menu

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import com.keygenqt.viewer.android.NavGraph
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Callback click bottom bar for [NavGraph]
 */
object MenuBottomBar {
    var onClick: ((MenuTab) -> Unit)? = null
}

/**
 * Bottom bar app
 */
@Composable
fun MenuBottomBar(
    destination: NavDestination?,
) {
    val scope = rememberCoroutineScope()
    if (MenuTab.values().any { it.route == destination?.route }) {
        NavigationBar {
            MenuTab.values().forEach { tab ->
                NavigationBarItem(
                    icon = { Icon(tab.icon, contentDescription = null) },
                    label = { Text(tab.name) },
                    selected = tab.route == destination?.route,
                    onClick = {
                        scope.launch {
                            delay(50)
                            MenuBottomBar.onClick?.invoke(tab)
                        }
                    }
                )
            }
        }
    }
}
