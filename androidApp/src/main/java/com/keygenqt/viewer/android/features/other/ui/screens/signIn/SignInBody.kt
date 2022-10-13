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
package com.keygenqt.viewer.android.features.other.ui.screens.signIn

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keygenqt.forms.base.FormFieldState
import com.keygenqt.forms.base.FormFieldsState
import com.keygenqt.modifier.imePaddingWithOutNavigationBars
import com.keygenqt.requests.ResponseState
import com.keygenqt.routing.NavigationDispatcher
import com.keygenqt.viewer.android.R
import com.keygenqt.viewer.android.compose.base.AppScaffold
import com.keygenqt.viewer.android.compose.base.FormError
import com.keygenqt.viewer.android.features.other.ui.actions.SignInActions
import com.keygenqt.viewer.android.features.other.ui.forms.SignInFieldsForm.SignInNickname
import com.keygenqt.viewer.android.theme.AppTheme
import com.keygenqt.viewer.android.utils.AppHelper

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignInBody(
    formFields: FormFieldsState,
    uriHandler: UriHandler? = null,
    state1: ResponseState = ResponseState.Start,
    navDispatcher: NavigationDispatcher? = null,
    onActions: (SignInActions) -> Unit = {},
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
            uriHandler?.openUri(AppHelper.getOauthLink(formFields.get(SignInNickname).getValue()))
            // hide keyboard
            softwareKeyboardController?.hide()
        }
    }

    // state query 1
    var loading by remember { mutableStateOf(false) }
    var error: Int? by remember { mutableStateOf(null) }

    SignInQueryState1(
        state = state1,
        loading = {
            loading = true
        },
        error = {
            error = it.resId
        },
        success = {
            onActions(SignInActions.ToRepos)
        },
        clear = {
            loading = false
            error = null
        }
    )

    AppScaffold(
        navigationDispatcher = navDispatcher,
        topBarTitle = stringResource(id = R.string.sign_in_title),
        topBarLoading = loading,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .imePaddingWithOutNavigationBars()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                error?.let {
                    FormError(text = stringResource(id = it))
                    Spacer(modifier = Modifier.size(16.dp))
                    LaunchedEffect(it) { scrollState.animateScrollTo(0) }
                }

                SignInForm(
                    loading = loading,
                    formFields = formFields,
                    submitClick = submitClick,
                )
            }

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Button(
                    enabled = !loading,
                    onClick = { submitClick.invoke() },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(stringResource(id = R.string.sign_in_form_button_submit).uppercase())
                }
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PIXEL_4)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.PIXEL_4)
@Composable
private fun Preview() {
    AppTheme {
        SignInBody(
            FormFieldsState().apply {
                add(SignInNickname, FormFieldState())
            }
        )
    }
}
