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
package com.keygenqt.viewer.android.features.other.ui.screens.startPage

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keygenqt.viewer.android.R
import com.keygenqt.viewer.android.base.viewModel.AppViewModel
import com.keygenqt.viewer.android.base.viewModel.ViewModelState
import com.keygenqt.viewer.android.compose.components.AppScaffold
import com.keygenqt.viewer.android.compose.components.FormError
import com.keygenqt.viewer.android.compose.components.lottie.StartPageAnimation
import com.keygenqt.viewer.android.features.other.ui.actions.StartPageActions
import com.keygenqt.viewer.android.theme.AppTheme

@Composable
fun StartPageBody(
    appViewModel: AppViewModel? = null,
    onActions: (StartPageActions) -> Unit = {},
    stateViewModel: ViewModelState = ViewModelState.Start,
) {
    AppScaffold {
        Box(Modifier) {
            Column(
                Modifier
                    .padding(30.dp)
                    .align(Alignment.Center)
                    .fillMaxSize()
            ) {
                StartPageAnimation()
            }
            Column(
                Modifier
                    .padding(16.dp)
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
            ) {
                stateViewModel.getErrorMessage()?.let {
                    FormError(text = it) {
                        Spacer(modifier = Modifier.size(8.dp))
                        OutlinedButton(
                            contentPadding = PaddingValues(
                                start = 16.dp,
                                top = 0.dp,
                                end = 16.dp,
                                bottom = 0.dp
                            ),
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = MaterialTheme.colorScheme.outline
                            ),
                            enabled = stateViewModel.isNotAction(),
                            onClick = { onActions(StartPageActions.StartLoadingData) },
                        ) {
                            Text(stringResource(id = R.string.start_page_try_again))
                        }
                    }
                }
            }

            stateViewModel.getErrorMessage()?.let {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.BottomEnd),
                ) {
                    OutlinedButton(
                        contentPadding = PaddingValues(
                            start = 16.dp,
                            top = 0.dp,
                            end = 16.dp,
                            bottom = 0.dp
                        ),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = MaterialTheme.colorScheme.outline
                        ),
                        enabled = stateViewModel.isNotAction(),
                        onClick = { onActions(StartPageActions.ToSignIn) },
                    ) {
                        Text(stringResource(id = R.string.start_page_logout))
                    }
                }
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PIXEL_4)
@Composable
private fun Preview() {
    AppTheme {
        StartPageBody()
    }
}
