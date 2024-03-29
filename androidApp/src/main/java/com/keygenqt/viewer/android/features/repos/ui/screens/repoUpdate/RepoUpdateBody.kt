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
package com.keygenqt.viewer.android.features.repos.ui.screens.repoUpdate

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keygenqt.forms.base.FormFieldsState
import com.keygenqt.modifier.imePaddingWithOutNavigationBars
import com.keygenqt.routing.NavigationDispatcher
import com.keygenqt.viewer.android.R
import com.keygenqt.viewer.android.compose.base.AppScaffold
import com.keygenqt.viewer.android.compose.base.FormError
import com.keygenqt.viewer.android.compose.base.FormSuccess
import com.keygenqt.viewer.android.features.repos.ui.actions.RepoUpdateActions
import com.keygenqt.viewer.android.features.repos.ui.forms.RepoUpdateForm.*
import com.keygenqt.viewer.android.features.repos.ui.forms.mockRepoUpdateForm
import com.keygenqt.viewer.android.theme.AppTheme

/**
 * Body for [RepoUpdateScreen]
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RepoUpdateBody(
    error: String?,
    loading: Boolean,
    success: Boolean,
    formFields: FormFieldsState,
    localFocusManager: FocusManager? = null,
    softwareKeyboardController: SoftwareKeyboardController? = null,
    navDispatcher: NavigationDispatcher? = null,
    onActions: (RepoUpdateActions) -> Unit = {},
) {
    val scrollState = rememberScrollState()

    // click submit
    val submitClick = {
        // validate before send
        formFields.validate()
        // check has errors
        if (!formFields.hasErrors()) {
            // clear focuses
            localFocusManager?.clearFocus()
            // submit query
            with(formFields) {
                onActions(
                    RepoUpdateActions.RepoUpdate(
                        name = get(RepoUpdateName).getValue(),
                        isPrivate = get(RepoUpdatePrivate).getValue().toBoolean(),
                        description = get(RepoUpdateDescription).getValue(),
                    )
                )
            }
            // hide keyboard
            softwareKeyboardController?.hide()
        }
    }

    AppScaffold(
        navigationDispatcher = navDispatcher,
        topBarLoading = loading,
        topBarTitle = stringResource(id = R.string.repo_update_title),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .imePaddingWithOutNavigationBars()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Column(Modifier.padding(start = 16.dp, end = 16.dp)) {
                Spacer(modifier = Modifier.size(16.dp))

                error?.let {
                    FormError(text = error.ifBlank { stringResource(id = R.string.error_exception_unknown) })
                    Spacer(modifier = Modifier.size(16.dp))
                    LaunchedEffect(it) { scrollState.animateScrollTo(0) }
                }

                if (success) {
                    FormSuccess(text = stringResource(id = R.string.settings_form_successfully))
                    Spacer(modifier = Modifier.size(16.dp))
                    LaunchedEffect(Unit) { scrollState.animateScrollTo(0) }
                }

                RepoUpdateForm(
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

@OptIn(ExperimentalComposeUiApi::class)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PIXEL_4)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.PIXEL_4)
@Composable
private fun Preview() {
    AppTheme {
        RepoUpdateBody(
            error = null,
            loading = false,
            success = false,
            formFields = mockRepoUpdateForm()
        )
    }
}
