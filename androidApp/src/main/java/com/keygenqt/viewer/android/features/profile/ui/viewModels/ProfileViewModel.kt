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
package com.keygenqt.viewer.android.features.profile.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keygenqt.viewer.android.data.models.toModel
import com.keygenqt.viewer.android.data.services.AppDataService
import com.keygenqt.viewer.android.extensions.withTransaction
import com.keygenqt.viewer.android.features.profile.ui.screens.profile.ProfileScreen
import com.keygenqt.viewer.android.data.services.impl.UserModelDataService
import com.keygenqt.viewer.data.storage.CrossStorage
import com.keygenqt.viewer.services.AppHttpClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * [ViewModel] for [ProfileScreen]
 */
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val client: AppHttpClient,
    private val dataService: AppDataService,
    private val storage: CrossStorage
) : ViewModel() {

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
     * Listen user model
     */
    val user = dataService.getUserModel().distinctUntilChanged()

    init {
        updateProfile()
    }

    /**
     * Query update profile
     */
    fun updateProfile() {
        viewModelScope.launch {
            _error.value = null
            _loading.value = true
            try {
                client.get.user().let {
                    dataService.withTransaction<UserModelDataService> {
                        clearUserModel()
                        insertUserModel(it.toModel())
                    }
                }
            } catch (ex: Exception) {
                _error.value = ex.localizedMessage ?: ""
            }
            _loading.value = false
        }
    }

    fun clearStorage() {
        storage.clearCache()
        storage.isOnboardingDone = true
    }
}
