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
package com.keygenqt.viewer.android.features.profile.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keygenqt.response.extensions.done
import com.keygenqt.response.extensions.error
import com.keygenqt.response.extensions.success
import com.keygenqt.viewer.android.data.requests.UserUpdateRequest
import com.keygenqt.viewer.android.services.apiService.AppApiService
import com.keygenqt.viewer.android.services.dataService.AppDataService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * [ViewModel] for feature
 */
@HiltViewModel
class ProfileViewModel @Inject constructor(
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
     *
     * @param success callback if success
     */
    fun userUpdate(
        name: String,
        blog: String,
        twitterUsername: String?,
        company: String,
        location: String,
        bio: String,
        success: () -> Unit,
    ) {
        _loading.value = true
        _error.value = null

        viewModelScope.launch {
            apiService.userUpdate(
                UserUpdateRequest(
                    name = name,
                    blog = blog,
                    twitter_username = twitterUsername,
                    company = company,
                    location = location,
                    bio = bio,
                )
            ).success {
                success()
            }.error {
                Timber.e(it)
                _error.value = it.message ?: "User update failed"
            }.done {
                _loading.value = false
            }
        }
    }
}
