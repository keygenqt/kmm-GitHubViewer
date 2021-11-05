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

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keygenqt.response.extensions.done
import com.keygenqt.response.extensions.error
import com.keygenqt.response.extensions.success
import com.keygenqt.viewer.android.data.models.SecurityModel
import com.keygenqt.viewer.android.extensions.withTransaction
import com.keygenqt.viewer.android.services.apiService.AppApiService
import com.keygenqt.viewer.android.services.dataService.AppDataService
import com.keygenqt.viewer.android.services.dataService.impl.SecurityModelDataService
import com.keygenqt.viewer.android.services.dataService.impl.UserModelDataService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * [ViewModel] for feature
 */
@HiltViewModel
class OtherViewModel @Inject constructor(
    private val apiService: AppApiService,
    private val dataService: AppDataService
) : ViewModel() {
    /**
     * [MutableStateFlow] for loading state
     */
    private val _loading: MutableStateFlow<Boolean> = MutableStateFlow(false)

    /**
     * [StateFlow] for [_loading]
     */
    val loading: StateFlow<Boolean> get() = _loading.asStateFlow()

    /**
     * [MutableStateFlow] for errors state
     */
    private val _error: MutableStateFlow<String?> = MutableStateFlow(null)

    /**
     * [StateFlow] for [_error]
     */
    val error: StateFlow<String?> get() = _error.asStateFlow()

    /**
     * Query login user with callback if success
     *
     * @param nickname login user
     * @param success callback if success with data user identifier and token
     */
    fun signIn(
        nickname: String,
        success: () -> Unit,
    ) {
        _error.value = null
        _loading.value = true
        viewModelScope.launch {
            apiService.getUser(nickname = nickname)
                .success { model ->
                    dataService.withTransaction<UserModelDataService> {
                        clearUserModel()
                        insertUserModel(model)
                    }
                    dataService.withTransaction<SecurityModelDataService> {
                        clearSecurityModel()
                        insertSecurityModel(
                            SecurityModel(
                                nickname = nickname
                            )
                        )
                    }
                    success.invoke()
                }
                .error {
                    _error.value = it.message ?: "Authentication failed"
                    Timber.e(it)
                }
                .done { _loading.value = false }
        }
    }
}
