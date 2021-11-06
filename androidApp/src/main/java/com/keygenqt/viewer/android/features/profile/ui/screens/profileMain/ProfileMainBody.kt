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
package com.keygenqt.viewer.android.features.profile.ui.screens.profileMain

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keygenqt.viewer.android.R
import com.keygenqt.viewer.android.compose.components.AppScaffold
import com.keygenqt.viewer.android.data.mock.mockUserModel
import com.keygenqt.viewer.android.data.models.UserModel
import com.keygenqt.viewer.android.extensions.navigationBarsPaddingMaterial3
import com.keygenqt.viewer.android.features.profile.ui.actions.ProfileMainActions
import com.keygenqt.viewer.android.theme.AppTheme

@Composable
fun ProfileMainBody(
    model: UserModel?,
    onActions: (ProfileMainActions) -> Unit = {},
) {
    AppScaffold(
        title = stringResource(id = R.string.profile_title)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPaddingMaterial3()
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                model?.let {

                    Text(
                        modifier = Modifier,
                        text = stringResource(id = R.string.profile_name_text, model.name)
                    )

                    OutlinedButton(
                        onClick = {
                            onActions(ProfileMainActions.Logout)
                        },
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(stringResource(id = R.string.profile_logout).uppercase())
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
        ProfileMainBody(mockUserModel())
    }
}
