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
package com.keygenqt.viewer.android.features.other.ui.screens.welcome

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keygenqt.viewer.android.R
import com.keygenqt.viewer.android.base.AppViewModel
import com.keygenqt.viewer.android.compose.components.AppScaffold
import com.keygenqt.viewer.android.compose.texts.TextTitleLarge
import com.keygenqt.viewer.android.features.other.ui.actions.WelcomeActions
import com.keygenqt.viewer.android.theme.AppTheme

@Composable
fun WelcomeBody(
    appViewModel: AppViewModel? = null,
    onActions: (WelcomeActions) -> Unit = {},
) {
    AppScaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .height(80.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                TextTitleLarge(
                    modifier = Modifier,
                    text = stringResource(id = R.string.welcome_title)
                )
            }
            Column(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            ) {
                WelcomeAnimation(
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Column(
                modifier = Modifier
            ) {
                Button(
                    onClick = {
                        onActions(WelcomeActions.ToSignIn)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(id = R.string.welcome_btn_sign_in).uppercase())
                }
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PIXEL_4)
@Composable
private fun Preview() {
    AppTheme {
        WelcomeBody()
    }
}
