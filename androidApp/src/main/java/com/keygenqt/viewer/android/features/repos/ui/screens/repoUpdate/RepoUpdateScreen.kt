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

import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.keygenqt.forms.base.FormFieldsState
import com.keygenqt.viewer.android.base.LocalNavigationDispatcher
import com.keygenqt.viewer.android.features.repos.ui.actions.RepoUpdateActions
import com.keygenqt.viewer.android.features.repos.ui.forms.RepoUpdateForm.*
import com.keygenqt.viewer.android.features.repos.ui.viewModels.RepoUpdateModel

/**
 * Base page fun for initialization
 *
 * @param viewModel page view model
 * @param onActions actions for page
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RepoUpdateScreen(
    viewModel: RepoUpdateModel,
    onActions: (RepoUpdateActions) -> Unit = {},
) {
    val model by viewModel.repo.collectAsState(null)
    val state1 by viewModel.query1.state.collectAsState()

    LaunchedEffect(model) {
        model?.let {
            RepoUpdateName.state.setValue(it.name)
            RepoUpdatePrivate.state.setValue(it.isPrivate.toString())
            RepoUpdateDescription.state.setValue(it.description)
        }
    }

    val formFields = remember {
        FormFieldsState().apply {
            add(RepoUpdateName, RepoUpdateName.state.default(""))
            add(RepoUpdatePrivate, RepoUpdatePrivate.state.default("false"))
            add(RepoUpdateDescription, RepoUpdateDescription.state.default(""))
        }
    }

    RepoUpdateBody(
        formFields = formFields,
        state1 = state1,
        onActions = onActions,
        localFocusManager = LocalFocusManager.current,
        softwareKeyboardController = LocalSoftwareKeyboardController.current,
        navDispatcher = LocalNavigationDispatcher.current
    )
}
