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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * Main [ViewModel] for app
 */
@HiltViewModel
class AppViewModel : ViewModel() {

    /**
     * [MutableStateFlow] for start app and end splash
     */
    private val _isReady: MutableStateFlow<Boolean> = MutableStateFlow(false)

    /**
     * [StateFlow] for [_isReady]
     */
    val isReady: StateFlow<Boolean> get() = _isReady.asStateFlow()

    /**
     * [MutableStateFlow] for start app and end splash
     */
    private val _topAppBar: MutableStateFlow<Int?> = MutableStateFlow(null)

    /**
     * [StateFlow] for [_topAppBar]
     */
    val topAppBar: StateFlow<Int?> get() = _topAppBar.asStateFlow()

    /**
     * [MutableStateFlow] for start app and end splash
     */
    private val _isScrollTopBar: MutableStateFlow<Boolean> = MutableStateFlow(false)

    /**
     * [StateFlow] for [_isScrollTopBar]
     */
    val isScrollTopBar: StateFlow<Boolean> get() = _isScrollTopBar.asStateFlow()

    /**
     * Set scroll state for scroll behavior
     */
    fun setScrollState(state: Boolean) {
        _isScrollTopBar.value = state
    }

    /**
     * Set top bar title
     */
    fun setTopAppBarTitle(title: Int?) {
        _topAppBar.value = title
    }
}
