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
package com.keygenqt.viewer.android.features.repos.ui.screens.repoUpdate

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
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
import com.keygenqt.viewer.android.compose.texts.TextLabelLarge
import com.keygenqt.viewer.android.extensions.AnimatedNavGraphState
import com.keygenqt.viewer.android.extensions.LaunchedEffectAnimation
import com.keygenqt.viewer.android.extensions.textFieldColors
import com.keygenqt.viewer.android.features.repos.ui.forms.RepoUpdateForm.*
import com.keygenqt.viewer.android.features.repos.ui.forms.mockRepoUpdateForm
import com.keygenqt.viewer.android.theme.AppTheme

@Composable
fun RepoUpdateForm(
    formFields: FormFieldsState,
    loading: Boolean = false,
) {
    val scope = rememberCoroutineScope()

    FormField(
        label = stringResource(id = R.string.repo_update_name),
        enabled = !loading,
        state = formFields.get(RepoUpdateName),
        imeAction = ImeAction.Next,
        keyboardType = KeyboardType.Text,
        keyboardActions = KeyboardActions(onNext = {
            formFields.get(RepoUpdateDescription).requestFocus()
        }),
        colors = MaterialTheme.textFieldColors()
    )

    Spacer(modifier = Modifier.size(16.dp))

    FormField(
        lines = 5,
        maxLines = 15,
        singleLine = false,
        label = stringResource(id = R.string.repo_update_description),
        enabled = !loading,
        state = formFields.get(RepoUpdateDescription),
        imeAction = ImeAction.Default,
        keyboardType = KeyboardType.Text,
        colors = MaterialTheme.textFieldColors()
    )

    Spacer(modifier = Modifier.size(16.dp))

    BlockSwitch(
        enabled = !loading,
        label = stringResource(id = R.string.repo_update_is_private),
        checked = formFields.get(RepoUpdatePrivate).getValue().toBoolean()
    ) { checked ->
        formFields.get(RepoUpdatePrivate).setValue(checked.toString())
    }

    LaunchedEffectAnimation { state ->
        if (state == AnimatedNavGraphState.END) {
            formFields.get(RepoUpdateName).requestFocus()
        }
    }
}

@Composable
private fun BlockSwitch(
    checked: Boolean,
    label: String,
    enabled: Boolean = true,
    onCheckedChange: (Boolean) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier.weight(1f),
        ) {
            TextLabelLarge(
                maxLines = 1,
                text = label,
            )
        }
        Switch(
            enabled = enabled,
            checked = checked,
            onCheckedChange = { onCheckedChange.invoke(!checked) },
            colors = SwitchDefaults.colors(checkedThumbColor = MaterialTheme.colorScheme.secondary),
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PIXEL_4)
@Composable
private fun Preview() {
    AppTheme {
        RepoUpdateForm(mockRepoUpdateForm())
    }
}
