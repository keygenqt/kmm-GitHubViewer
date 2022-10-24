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

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keygenqt.requests.RequestHandler
import com.keygenqt.requests.isSucceeded
import com.keygenqt.requests.success
import com.keygenqt.viewer.android.R
import com.keygenqt.viewer.android.base.exceptions.ResponseException
import com.keygenqt.viewer.android.data.preferences.BasePreferences
import com.keygenqt.viewer.android.extensions.withTransaction
import com.keygenqt.viewer.android.services.apiService.AppApiService
import com.keygenqt.viewer.android.services.dataService.AppDataService
import com.keygenqt.viewer.android.services.dataService.impl.SecurityModelDataService
import com.keygenqt.viewer.android.services.dataService.impl.UserModelDataService
import com.keygenqt.viewer.android.utils.AuthUser
import com.keygenqt.viewer.base.StorageKMM
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
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
    private val storage: StorageKMM,
    @ApplicationContext context: Context
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
    var isOnboardingDone = lazy { preferences.isOnboardingDone }

    init {
        // Listen errors responses
        viewModelScope.launch {
            RequestHandler.singleCollect {
                val message = (it as? ResponseException)
                    ?.let { ex -> context.getString(ex.resId) }
                    ?: it.message
                    ?: context.getString(R.string.error_something_wrong)
                // show toast
                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                // logcat
                Timber.e(it)
            }
        }

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
                        Timber.e("Start app User")
                    }
                    else -> {
                        dataService.clearCacheAfterLogout()
                        Timber.e("Start app Guest")
                    }
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

        Timber.e("---------------")
        Timber.e(storage.isOnboardingDone.toString())
        storage.isOnboardingDone = !storage.isOnboardingDone
        Timber.e(storage.isOnboardingDone.toString())
        Timber.e("--------------- clearCache")
        storage.clearCache()
        Timber.e(storage.isOnboardingDone.toString())
        Timber.e("---------------")
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
