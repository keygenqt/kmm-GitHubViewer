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
package com.keygenqt.viewer.android.features.other.ui.screens.signIn

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keygenqt.forms.base.FormFieldState
import com.keygenqt.forms.base.FormFieldsState
import com.keygenqt.forms.fields.FormField
import com.keygenqt.viewer.android.R
import com.keygenqt.viewer.android.extensions.textFieldColors
import com.keygenqt.viewer.android.features.other.ui.forms.SignInFieldsForm.SignInNickname
import com.keygenqt.viewer.android.theme.AppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Form page [SignInBody]
 *
 * @param loading state call query to api
 * @param submitClick submit
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignInForm(
    formFields: FormFieldsState,
    loading: Boolean = false,
    submitClick: () -> Unit = {},
) {
    val scope = rememberCoroutineScope()

    Column {

        FormField(
            label = stringResource(id = R.string.sign_in_form_nickname),
            enabled = !loading,
            state = formFields.get(SignInNickname),
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Text,
            keyboardActions = KeyboardActions(onDone = { submitClick.invoke() }),
            colors = MaterialTheme.textFieldColors()
        )

        // Clear focus after end
        LaunchedEffect(Unit) {
            scope.launch {
                delay(10)
                formFields.get(SignInNickname).requestFocus()
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PIXEL_4)
@Composable
private fun Preview() {
    AppTheme {
        SignInForm(
            FormFieldsState().apply {
                add(SignInNickname, FormFieldState())
            }
        )
    }
}
