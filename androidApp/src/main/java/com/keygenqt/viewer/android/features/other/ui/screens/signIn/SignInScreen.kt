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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalUriHandler
import com.keygenqt.forms.base.FormFieldsState
import com.keygenqt.viewer.android.BuildConfig
import com.keygenqt.viewer.android.base.LocalNavigationDispatcher
import com.keygenqt.viewer.android.features.other.ui.actions.SignInActions
import com.keygenqt.viewer.android.features.other.ui.forms.SignInFieldsForm.SignInNickname
import com.keygenqt.viewer.android.features.other.ui.viewModels.SignInViewModel
import com.keygenqt.viewer.utils.AppConstants

/**
 * Base page fun for initialization
 *
 * @param viewModel page view model
 * @param onActions actions for page
 */
@Composable
fun SignInScreen(
    viewModel: SignInViewModel,
    onActions: (SignInActions) -> Unit = {},
) {
    val state1 by viewModel.query1.state.collectAsState()

    val formFields = remember {
        FormFieldsState().apply {
            add(SignInNickname, SignInNickname.state.default(if (BuildConfig.DEBUG) AppConstants.DEBUG_CREDENTIAL_LOGIN else ""))
        }
    }

    SignInBody(
        state1 = state1,
        onActions = onActions,
        formFields = formFields,
        uriHandler = LocalUriHandler.current,
        navDispatcher = LocalNavigationDispatcher.current
    )
}
