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
package com.keygenqt.viewer.android.features.profile.ui.screens.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.keygenqt.forms.base.FormFieldsState
import com.keygenqt.viewer.android.data.models.UserModel
import com.keygenqt.viewer.android.features.profile.ui.actions.SettingsActions
import com.keygenqt.viewer.android.features.profile.ui.forms.UserUpdateForm.*
import com.keygenqt.viewer.android.features.profile.ui.viewModels.ProfileViewModel

/**
 * Base page fun for initialization
 *
 * @param viewModel page view model
 * @param onActions actions for page
 */
@Composable
fun SettingsScreen(
    viewModel: ProfileViewModel,
    onActions: (SettingsActions) -> Unit = {},
) {

    val model: UserModel? by viewModel.user.collectAsState(null)
    val error: String? by viewModel.error.collectAsState(null)
    val loading: Boolean by viewModel.loading.collectAsState()

    model?.let {
        UserUpdateName.state.setValue(it.name)
        UserUpdateBlog.state.setValue(it.blog)
        UserUpdateTwitter.state.setValue(it.twitterUsername)
        UserUpdateCompany.state.setValue(it.company)
        UserUpdateLocation.state.setValue(it.location)
        UserUpdateBio.state.setValue(it.bio)
    }

    val formFields = remember {
        FormFieldsState().apply {
            add(UserUpdateName, UserUpdateName.state.default(""))
            add(UserUpdateBlog, UserUpdateBlog.state.default(""))
            add(UserUpdateTwitter, UserUpdateTwitter.state.default(""))
            add(UserUpdateCompany, UserUpdateCompany.state.default(""))
            add(UserUpdateLocation, UserUpdateLocation.state.default(""))
            add(UserUpdateBio, UserUpdateBio.state.default(""))
        }
    }

    SettingsBody(
        error = error,
        loading = loading,
        formFields = formFields,
        onActions = onActions
    )
}
