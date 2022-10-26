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
package com.keygenqt.viewer.android.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keygenqt.viewer.android.data.models.toModel
import com.keygenqt.viewer.android.data.services.AppDataService
import com.keygenqt.viewer.android.extensions.withTransaction
import com.keygenqt.viewer.android.data.services.impl.SecurityModelDataService
import com.keygenqt.viewer.android.data.services.impl.UserModelDataService
import com.keygenqt.viewer.android.utils.AuthUser
import com.keygenqt.viewer.data.storage.CrossStorage
import com.keygenqt.viewer.services.AppHttpClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Main [ViewModel] for app
 */
@HiltViewModel
class AppViewModel @Inject constructor(
    private val client: AppHttpClient,
    private val dataService: AppDataService,
    private val storage: CrossStorage,
) : ViewModel() {

    /**
     * [MutableStateFlow] for start app and end splash
     */
    private val _isSplash: MutableStateFlow<Boolean> = MutableStateFlow(false)
/**/
    /**
     * [StateFlow] for [_isSplash]
     */
    val isSplash: StateFlow<Boolean> get() = _isSplash.asStateFlow()

    /**
     * Check is show onboarding
     */
    var isOnboardingDone = lazy { storage.isOnboardingDone }

    init {
        // Listen refresh auth user
        viewModelScope.launch {
            AuthUser.singleCollectRefresh {
                dataService.withTransaction<SecurityModelDataService> {
                    updateSecurityModel(it)
                }
                Timber.e("Refresh token User")
            }
        }

        // Listen auth user change
        viewModelScope.launch {
            AuthUser.singleCollectAuth {
                _isSplash.value = true
                when {
                    it.isLogin -> {
                        // queries after login
                        queryRequiredForApp()
                        // get security model
                        val model = it.data!!
                        // update security model
                        dataService.withTransaction<SecurityModelDataService> {
                            clearSecurityModel()
                            insertSecurityModel(model)
                        }
                        client.setToken(it.data?.accessToken ?: "")
                    }
                    else -> {
                        dataService.clearCacheAfterLogout()
                        Timber.e("Start app Guest")
                        client.clearToken()
                    }
                }
                _isSplash.value = false
            }
        }

        // Start app auth token
        viewModelScope.launch {
            dataService.getSecurityModel()?.let {
                AuthUser.login(it)
                client.setToken(it.accessToken)
            } ?: run {
                client.clearToken()
                AuthUser.logout()
            }
        }
    }

    /**
     * Start loading data user
     */
    private suspend fun queryRequiredForApp() {
        // first query get user
        try {
            val response = client.get.user()
            dataService.withTransaction<UserModelDataService> {
                clearUserModel()
                insertUserModel(response.toModel())
            }
        } catch (ex: Exception) {
            // @todo
        }
    }
}
