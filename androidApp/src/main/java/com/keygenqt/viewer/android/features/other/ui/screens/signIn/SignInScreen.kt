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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.keygenqt.forms.base.FormFieldsState
import com.keygenqt.viewer.android.features.other.ui.actions.SignInActions
import com.keygenqt.viewer.android.features.other.ui.forms.SignInFieldsForm.SignInNickname
import com.keygenqt.viewer.android.features.other.ui.viewModels.OtherViewModel
import com.keygenqt.viewer.android.utils.ConstantsApp.DEBUG_CREDENTIAL_LOGIN

/**
 * Base page fun for initialization
 *
 * @param viewModel page view model
 * @param onActions actions for page
 */
@Composable
fun SignInScreen(
    viewModel: OtherViewModel,
    onActions: (SignInActions) -> Unit = {},
) {
    // Create from state
    val formFields = FormFieldsState().apply {
        add(SignInNickname, remember { SignInNickname.state.default(DEBUG_CREDENTIAL_LOGIN) })
    }

    val error: String? by viewModel.error.collectAsState(null)
    val loading: Boolean by viewModel.loading.collectAsState()

    SignInBody(
        error = error,
        loading = loading,
        onActions = onActions,
        formFields = formFields
    )
}
