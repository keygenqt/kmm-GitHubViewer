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
package com.keygenqt.viewer.android.features.profile.ui.forms

import com.keygenqt.forms.base.FormFieldState
import com.keygenqt.forms.base.FormFieldsState
import com.keygenqt.forms.base.FormStates
import com.keygenqt.viewer.android.features.profile.ui.forms.UserUpdateForm.*

/**
 * Form states for sign in
 */
enum class UserUpdateForm(val state: FormFieldState) : FormStates {
    UserUpdateName(FormFieldState()),
    UserUpdateBlog(FormFieldState()),
    UserUpdateTwitter(FormFieldState()),
    UserUpdateCompany(FormFieldState()),
    UserUpdateLocation(FormFieldState()),
    UserUpdateBio(FormFieldState()),
}

fun mockUserUpdateForm() = FormFieldsState().apply {
    add(UserUpdateName, FormFieldState())
    add(UserUpdateBlog, FormFieldState())
    add(UserUpdateTwitter, FormFieldState())
    add(UserUpdateCompany, FormFieldState())
    add(UserUpdateLocation, FormFieldState())
    add(UserUpdateBio, FormFieldState())
}