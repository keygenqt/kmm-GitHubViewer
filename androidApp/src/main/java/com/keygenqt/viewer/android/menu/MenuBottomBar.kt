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
package com.keygenqt.viewer.android.menu

import android.content.res.Configuration
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.keygenqt.viewer.android.base.AppActions
import com.keygenqt.viewer.android.features.repos.ui.screens.followersMain.ReposMainBody
import com.keygenqt.viewer.android.menu.MenuTab.Companion.findByRoute
import com.keygenqt.viewer.android.theme.AppTheme

val bottomBar: (AppActions) -> @Composable (String) -> @Composable () -> Unit = { appActions ->
    { route ->
        {
            route.findByRoute()?.let { tab ->
                MenuBottomBar(
                    currentRoute = tab,
                    appActions = appActions
                )
            }
        }
    }
}

@Composable
fun MenuBottomBar(
    appActions: AppActions,
    currentRoute: MenuTab = MenuTab.REPOS,
) {
    NavigationBar {
        MenuTab.values().forEach { tab ->
            NavigationBarItem(
                icon = { Icon(tab.icon, contentDescription = null) },
                label = { Text(tab.name) },
                selected = tab.route == currentRoute.route,
                onClick = {
                    when (tab) {
                        MenuTab.REPOS -> appActions.toReposMain()
                        MenuTab.FOLLOWERS -> appActions.toFollowersMain()
                        MenuTab.STATS -> appActions.toStatsMain()
                    }
                }
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PIXEL_4)
@Composable
private fun Preview() {
    AppTheme {
        ReposMainBody()
    }
}
