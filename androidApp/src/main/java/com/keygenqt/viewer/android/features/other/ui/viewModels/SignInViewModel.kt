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
package com.keygenqt.viewer.android.features.other.ui.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keygenqt.viewer.android.data.models.toModel
import com.keygenqt.viewer.android.features.other.ui.screens.signIn.SignInScreen
import com.keygenqt.viewer.android.utils.AuthUser
import com.keygenqt.viewer.data.responses.SecurityModel
import com.keygenqt.viewer.services.AppHttpClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * [ViewModel] for [SignInScreen]
 */
@HiltViewModel
class SignInViewModel @Inject constructor(
    private val client: AppHttpClient,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    /**
     * Arg deep link code
     */
    val code: String? = savedStateHandle["code"]

    /**
     * Arg deep link state
     */
    val state: String? = savedStateHandle["state"]

    /**
     * Error response
     */
    private val _error: MutableStateFlow<String?> = MutableStateFlow(null)

    /**
     * [StateFlow] for [_error]
     */
    val error: StateFlow<String?> get() = _error.asStateFlow()

    /**
     * Loading query
     */
    private val _loading: MutableStateFlow<Boolean> = MutableStateFlow(false)

    /**
     * [StateFlow] for [_loading]
     */
    val loading: StateFlow<Boolean> get() = _loading.asStateFlow()

    /**
     * Error response
     */
    private val _response: MutableStateFlow<SecurityModel?> = MutableStateFlow(null)

    /**
     * [StateFlow] for [_response]
     */
    val response: StateFlow<SecurityModel?> get() = _response.asStateFlow()

    init {
        code?.let {
            signInCode(it)
        }
    }

    private fun signInCode(code: String) {
        viewModelScope.launch {
            _error.value = null
            _loading.value = true
            try {
                client.post.oauth(code = code).let {
                    AuthUser.login(it.toModel())
                    _response.value = it
                }
            } catch (ex: Exception) {
                _error.value = ex.localizedMessage ?: ""
            }
            _loading.value = false
        }
    }
}
