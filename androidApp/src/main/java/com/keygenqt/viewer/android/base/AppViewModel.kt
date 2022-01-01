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
package com.keygenqt.viewer.android.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keygenqt.response.extensions.isSucceeded
import com.keygenqt.response.extensions.success
import com.keygenqt.viewer.android.data.preferences.BasePreferences
import com.keygenqt.viewer.android.extensions.withTransaction
import com.keygenqt.viewer.android.services.apiService.AppApiService
import com.keygenqt.viewer.android.services.dataService.AppDataService
import com.keygenqt.viewer.android.services.dataService.impl.SecurityModelDataService
import com.keygenqt.viewer.android.services.dataService.impl.UserModelDataService
import com.keygenqt.viewer.android.utils.AuthUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

/**
 * Main [ViewModel] for app
 */
@HiltViewModel
class AppViewModel @Inject constructor(
    private val apiService: AppApiService,
    private val dataService: AppDataService,
    private val preferences: BasePreferences,
) : ViewModel() {

    /**
     * [MutableStateFlow] for start app and end splash
     */
    private val _isSplash: MutableStateFlow<Boolean> = MutableStateFlow(false)

    /**
     * [StateFlow] for [_isSplash]
     */
    val isSplash: StateFlow<Boolean> get() = _isSplash.asStateFlow()

    /**
     * Check is show onboarding
     */
    var isOnboardingDone = lazy { preferences.isOnboardingDone }

    init {
        // Listen auth user change
        viewModelScope.launch {
            AuthUser.singleCollect {
                _isSplash.value = true
                if (it.isLogin) {
                    // queries after login
                    queryRequiredForApp()
                    // get security model
                    val model = it.data!!
                    // update security model
                    dataService.withTransaction<SecurityModelDataService> {
                        clearSecurityModel()
                        insertSecurityModel(model)
                    }
                    Timber.e("Start app User")
                } else {
                    dataService.clearCacheAfterLogout()
                    Timber.e("Start app Guest")
                }
                _isSplash.value = false
            }
        }

        // Start app auth token
        viewModelScope.launch {
            dataService.getSecurityModel()?.let {
                AuthUser.login(it)
            } ?: run {
                AuthUser.logout()
            }
        }
    }

    /**
     * Start loading data user
     */
    private suspend fun queryRequiredForApp() {
        // first query get user
        val responseUser = withContext(Dispatchers.IO) { apiService.getUser() }
        if (responseUser.isSucceeded) {
            responseUser.success {
                dataService.withTransaction<UserModelDataService> {
                    clearUserModel()
                    insertUserModel(it)
                }
            }
        }
    }
}
