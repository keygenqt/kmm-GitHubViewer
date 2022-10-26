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
import com.keygenqt.viewer.android.data.services.impl.UserModelDataService
import com.keygenqt.viewer.android.extensions.withTransaction
import com.keygenqt.viewer.android.features.profile.ui.screens.settings.SettingsScreen
import com.keygenqt.viewer.data.requests.UserRequest
import com.keygenqt.viewer.services.AppHttpClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * [ViewModel] for [SettingsScreen]
 */
@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val client: AppHttpClient,
    private val dataService: AppDataService
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
     * Success query
     */
    private val _success: MutableStateFlow<Boolean> = MutableStateFlow(false)

    /**
     * [StateFlow] for [_loading]
     */
    val success: StateFlow<Boolean> get() = _success.asStateFlow()

    /**
     * Listen user model
     */
    val user = dataService.getUserModel().distinctUntilChanged()

    /**
     * Update user profile
     *
     * @property name the new name of the user
     * @property blog the new blog URL of the user
     * @property twitterUsername the new Twitter username of the user
     * @property company the new company of the user
     * @property location the new location of the user
     * @property bio the new short biography of the user
     */
    fun userUpdate(
        name: String,
        blog: String,
        twitterUsername: String?,
        company: String,
        location: String,
        bio: String,
    ) {
        viewModelScope.launch {
            _error.value = null
            _loading.value = true
            _success.value = false
            try {
                client.patch.updateUser(
                    UserRequest(
                        name = name,
                        blog = blog,
                        twitterUsername = twitterUsername,
                        company = company,
                        location = location,
                        bio = bio,
                    )
                ).let {
                    dataService.withTransaction<UserModelDataService> {
                        clearUserModel()
                        insertUserModel(it.toModel())
                        _success.value = true
                    }
                }
            } catch (ex: Exception) {
                _error.value = ex.localizedMessage ?: ""
            }
            _loading.value = false
        }
    }
}
