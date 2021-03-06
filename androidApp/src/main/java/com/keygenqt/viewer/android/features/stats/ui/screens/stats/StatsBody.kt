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
package com.keygenqt.viewer.android.features.stats.ui.screens.stats

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keygenqt.githubviewer.Greeting
import com.keygenqt.viewer.android.R
import com.keygenqt.routing.NavigationDispatcher
import com.keygenqt.viewer.android.compose.base.AppScaffold
import com.keygenqt.viewer.android.features.stats.ui.actions.StatsActions
import com.keygenqt.viewer.android.theme.AppTheme

@Composable
fun StatsBody(
    navDispatcher: NavigationDispatcher? = null,
    onActions: (StatsActions) -> Unit = {},
) {
    val scrollState = rememberScrollState()

    AppScaffold(
        navigationDispatcher = navDispatcher,
        topBarTitle = stringResource(id = R.string.stats_title),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.tertiaryContainer)
                .verticalScroll(scrollState)
        ) {
            Column(Modifier.padding(20.dp)) {
                Text(
                    modifier = Modifier,
                    text = Greeting().greeting()
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

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PIXEL_4)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.PIXEL_4)
@Composable
private fun Preview() {
    AppTheme {
        StatsBody()
    }
}
