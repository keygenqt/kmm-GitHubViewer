/**
 * Copyright © 2021 Surf. All rights reserved.
 */
package com.keygenqt.viewer.android.base

import androidx.compose.foundation.ScrollState
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
class AppViewModel @Inject constructor() : ViewModel() {

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
