/**
 * Copyright © 2021 Surf. All rights reserved.
 */
package com.keygenqt.viewer.android.features.stats.ui.viewModels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * [ViewModel] for feature
 */
@HiltViewModel
class StatsViewModel @Inject constructor() : ViewModel()
