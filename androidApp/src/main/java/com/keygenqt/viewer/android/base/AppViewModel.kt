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

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keygenqt.viewer.android.extensions.withTransaction
import com.keygenqt.viewer.android.interfaces.IAppNavActions
import com.keygenqt.viewer.android.menu.bottomBar
import com.keygenqt.viewer.android.services.apiService.AppApiService
import com.keygenqt.viewer.android.services.dataService.AppDataService
import com.keygenqt.viewer.android.services.dataService.impl.SecurityModelDataService
import com.keygenqt.viewer.android.services.dataService.impl.UserModelDataService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Main [ViewModel] for app
 */
@HiltViewModel
class AppViewModel @Inject constructor(
    private val apiService: AppApiService,
    private val dataService: AppDataService
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
     * Bottom bar
     */
    private var _appBottomBar: (@Composable () -> Unit)? = null

    /**
     * Set action for bottom bar
     */
    fun setBottomBarActions(actions: IAppNavActions) {
        _appBottomBar = bottomBar.invoke(actions)
    }

    /**
     * Get bottom bar
     */
    fun getBottomBar(): (@Composable () -> Unit)? {
        return _appBottomBar
    }

    /**
     * Set is ready true
     */
    fun disableSplash() {
        _isSplash.value = true
    }

    /**
     * Flow for listen change status user
     */
    val isLogin = flow {
        dataService.getSecurityModel().distinctUntilChanged().collect {
            emit(it != null)
        }
    }

    fun logout(success: () -> Unit) {
        viewModelScope.launch {
            dataService.withTransaction<UserModelDataService> {
                clearUserModel()
            }
            dataService.withTransaction<SecurityModelDataService> {
                clearSecurityModel()
            }
            success.invoke()
        }
    }
}
