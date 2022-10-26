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
package com.keygenqt.viewer.android.features.profile.ui.screens.settings

import android.content.res.Configuration
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keygenqt.forms.base.FormFieldsState
import com.keygenqt.forms.fields.FormField
import com.keygenqt.viewer.android.R
import com.keygenqt.viewer.android.extensions.AnimatedNavGraphState
import com.keygenqt.viewer.android.extensions.LaunchedEffectAnimation
import com.keygenqt.viewer.android.extensions.textFieldColors
import com.keygenqt.viewer.android.features.profile.ui.forms.UserUpdateForm.*
import com.keygenqt.viewer.android.features.profile.ui.forms.mockUserUpdateForm
import com.keygenqt.viewer.android.theme.AppTheme

/**
 * Form page [SettingsBody]
 *
 * @param loading state call query to api
 */
@Composable
fun SettingsForm(
    formFields: FormFieldsState,
    loading: Boolean = false,
) {
    FormField(
        label = stringResource(id = R.string.settings_form_name),
        enabled = !loading,
        state = formFields.get(UserUpdateName),
        imeAction = ImeAction.Next,
        keyboardType = KeyboardType.Text,
        keyboardActions = KeyboardActions(onNext = {
            formFields.get(UserUpdateBlog).requestFocus()
        }),
        colors = MaterialTheme.textFieldColors()
    )

    Spacer(modifier = Modifier.size(16.dp))

    FormField(
        label = stringResource(id = R.string.settings_form_blog),
        enabled = !loading,
        state = formFields.get(UserUpdateBlog),
        imeAction = ImeAction.Next,
        keyboardType = KeyboardType.Uri,
        keyboardActions = KeyboardActions(onNext = {
            formFields.get(UserUpdateTwitter).requestFocus()
        }),
        colors = MaterialTheme.textFieldColors()
    )

    Spacer(modifier = Modifier.size(16.dp))

    FormField(
        label = stringResource(id = R.string.settings_form_twitter_username),
        enabled = !loading,
        state = formFields.get(UserUpdateTwitter),
        imeAction = ImeAction.Next,
        keyboardType = KeyboardType.Text,
        keyboardActions = KeyboardActions(onNext = {
            formFields.get(UserUpdateCompany).requestFocus()
        }),
        colors = MaterialTheme.textFieldColors()
    )

    Spacer(modifier = Modifier.size(16.dp))

    FormField(
        label = stringResource(id = R.string.settings_form_company),
        enabled = !loading,
        state = formFields.get(UserUpdateCompany),
        imeAction = ImeAction.Next,
        keyboardType = KeyboardType.Text,
        keyboardActions = KeyboardActions(onNext = {
            formFields.get(UserUpdateLocation).requestFocus()
        }),
        colors = MaterialTheme.textFieldColors()
    )

    Spacer(modifier = Modifier.size(16.dp))

    FormField(
        label = stringResource(id = R.string.settings_form_location),
        enabled = !loading,
        state = formFields.get(UserUpdateLocation),
        imeAction = ImeAction.Next,
        keyboardType = KeyboardType.Text,
        keyboardActions = KeyboardActions(onNext = {
            formFields.get(UserUpdateBio).requestFocus()
        }),
        colors = MaterialTheme.textFieldColors()
    )

    Spacer(modifier = Modifier.size(16.dp))

    FormField(
        lines = 3,
        maxLines = 5,
        singleLine = false,
        label = stringResource(id = R.string.settings_form_bio),
        enabled = !loading,
        state = formFields.get(UserUpdateBio),
        imeAction = ImeAction.Default,
        keyboardType = KeyboardType.Text,
        colors = MaterialTheme.textFieldColors()
    )

    LaunchedEffectAnimation { state ->
        if (state == AnimatedNavGraphState.END) {
            formFields.get(UserUpdateName).requestFocus()
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PIXEL_4)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.PIXEL_4)
@Composable
private fun Preview() {
    AppTheme {
        SettingsForm(mockUserUpdateForm())
    }
}
