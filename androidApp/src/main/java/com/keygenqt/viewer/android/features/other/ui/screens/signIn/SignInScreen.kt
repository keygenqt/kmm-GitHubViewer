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

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalUriHandler
import com.keygenqt.forms.base.FormFieldsState
import com.keygenqt.viewer.android.base.LocalViewModel
import com.keygenqt.viewer.android.features.other.ui.actions.SignInActions
import com.keygenqt.viewer.android.features.other.ui.forms.SignInFieldsForm.SignInNickname
import com.keygenqt.viewer.android.features.other.ui.viewModels.SignInViewModel
import com.keygenqt.viewer.android.utils.ConstantsApp.DEBUG_CREDENTIAL_LOGIN

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
    val isLogin by LocalViewModel.current.isLogin.collectAsState(false)

    // open main page if user login
    LaunchedEffect(isLogin) {
        if (isLogin) {
            onActions(SignInActions.ToStartPage)
        }
    }

    val formFields = remember {
        FormFieldsState().apply {
            add(SignInNickname, SignInNickname.state.default(DEBUG_CREDENTIAL_LOGIN))
        }
    }

    SignInBody(
        state1 = state1,
        onActions = onActions,
        formFields = formFields,
        uriHandler = LocalUriHandler.current
    )
}
