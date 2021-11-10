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
package com.keygenqt.viewer.android.features.profile.ui.screens.settings

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keygenqt.forms.base.FormFieldsState
import com.keygenqt.viewer.android.R
import com.keygenqt.viewer.android.compose.components.AppScaffold
import com.keygenqt.viewer.android.compose.components.FormError
import com.keygenqt.viewer.android.features.profile.ui.actions.SettingsActions
import com.keygenqt.viewer.android.features.profile.ui.forms.UserUpdateForm.*
import com.keygenqt.viewer.android.features.profile.ui.forms.mockUserUpdateForm
import com.keygenqt.viewer.android.theme.AppTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SettingsBody(
    formFields: FormFieldsState,
    error: String? = null,
    loading: Boolean = false,
    onActions: (SettingsActions) -> Unit = {},
) {
    val scrollState = rememberScrollState()
    val softwareKeyboardController = LocalSoftwareKeyboardController.current
    val localFocusManager = LocalFocusManager.current

    // click submit
    val submitClick = {
        // validate before send
        formFields.validate()
        // check has errors
        if (!formFields.hasErrors()) {
            // clear focuses
            localFocusManager.clearFocus()
            // submit query
            onActions(
                SettingsActions.UserUpdate(
                    name = formFields.get(UserUpdateName).getValue(),
                    blog = formFields.get(UserUpdateBlog).getValue(),
                    twitterUsername = formFields.get(UserUpdateTwitter).getValue(),
                    company = formFields.get(UserUpdateCompany).getValue(),
                    location = formFields.get(UserUpdateLocation).getValue(),
                    bio = formFields.get(UserUpdateBio).getValue()
                )
            )
            // hide keyboard
            softwareKeyboardController?.hide()
        }
    }

    AppScaffold(
        title = stringResource(id = R.string.settings_title),
        scrollState = scrollState,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Column(Modifier.padding(start = 16.dp, end = 16.dp)) {
                Spacer(modifier = Modifier.size(16.dp))

                error?.let {
                    FormError(text = it)
                    Spacer(modifier = Modifier.size(16.dp))
                    LaunchedEffect(error) { scrollState.animateScrollTo(0) }
                }

                SettingsForm(
                    loading = loading,
                    formFields = formFields,
                )

                Spacer(modifier = Modifier.size(16.dp))
            }

            Column(Modifier.padding(start = 16.dp, end = 16.dp)) {
                Button(
                    enabled = !loading,
                    onClick = { submitClick.invoke() },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(stringResource(id = R.string.settings_form_button_submit).uppercase())
                }

                Spacer(modifier = Modifier.size(16.dp))
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PIXEL_4)
@Composable
private fun Preview() {
    AppTheme {
        SettingsBody(mockUserUpdateForm())
    }
}
