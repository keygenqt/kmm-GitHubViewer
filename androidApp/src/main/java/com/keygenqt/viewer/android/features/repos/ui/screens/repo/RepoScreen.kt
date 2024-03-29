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
package com.keygenqt.viewer.android.features.repos.ui.screens.repo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.keygenqt.viewer.android.base.LocalNavigationDispatcher
import com.keygenqt.viewer.android.features.repos.ui.actions.RepoActions
import com.keygenqt.viewer.android.features.repos.ui.viewModels.RepoViewModel

/**
 * Base page fun for initialization
 *
 * @param viewModel page view model
 * @param onActions actions for page
 */
@Composable
fun RepoScreen(
    viewModel: RepoViewModel,
    onActions: (RepoActions) -> Unit = {},
) {
    val model by viewModel.repo.collectAsState(false)
    val error by viewModel.error.collectAsState()
    val loading by viewModel.loading.collectAsState()

    RepoBody(
        model = model,
        error = error,
        loading = loading,
        onActions = onActions,
        navDispatcher = LocalNavigationDispatcher.current
    )
}
