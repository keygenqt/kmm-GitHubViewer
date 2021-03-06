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
package com.keygenqt.viewer.android.features.repos.ui.forms

import com.keygenqt.forms.base.FormFieldState
import com.keygenqt.forms.base.FormFieldsState
import com.keygenqt.forms.base.FormStates
import com.keygenqt.viewer.android.features.repos.ui.forms.RepoUpdateForm.*

/**
 * Form states for update repo
 */
enum class RepoUpdateForm(val state: FormFieldState) : FormStates {
    RepoUpdateName(FormFieldState()),
    RepoUpdatePrivate(FormFieldState()),
    RepoUpdateDescription(FormFieldState()),
}

fun mockRepoUpdateForm() = FormFieldsState().apply {
    add(RepoUpdateName, FormFieldState())
    add(RepoUpdatePrivate, FormFieldState())
    add(RepoUpdateDescription, FormFieldState())
}
