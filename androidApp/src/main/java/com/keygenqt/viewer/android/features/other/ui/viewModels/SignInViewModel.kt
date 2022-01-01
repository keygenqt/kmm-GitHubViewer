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
package com.keygenqt.viewer.android.features.other.ui.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.keygenqt.response.extensions.success
import com.keygenqt.viewer.android.base.queryActions.QueryActions
import com.keygenqt.viewer.android.features.other.ui.screens.signIn.SignInScreen
import com.keygenqt.viewer.android.services.apiService.AppApiService
import com.keygenqt.viewer.android.services.dataService.AppDataService
import com.keygenqt.viewer.android.utils.AuthUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * [ViewModel] for [SignInScreen]
 */
@HiltViewModel
class SignInViewModel @Inject constructor(
    private val apiService: AppApiService,
    private val dataService: AppDataService,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    /**
     * State actions
     */
    val query1 = QueryActions(this)

    /**
     * Arg deep link code
     */
    val code: String? = savedStateHandle.get("code")

    /**
     * Arg deep link state
     */
    val state: String? = savedStateHandle.get("state")

    init {
        code?.let {
            signInCode(it)
        }
    }

    private fun signInCode(code: String) {
        query1.queryLaunch {
            apiService.oauthCode(code = code).success { AuthUser.login(it) }
        }
    }
}
