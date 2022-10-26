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
package com.keygenqt.viewer.android.features.repos.ui.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keygenqt.viewer.android.data.models.toModel
import com.keygenqt.viewer.android.data.services.AppDataService
import com.keygenqt.viewer.android.data.services.impl.RepoModelDataService
import com.keygenqt.viewer.android.extensions.withTransaction
import com.keygenqt.viewer.android.features.repos.navigation.route.ReposNavRoute
import com.keygenqt.viewer.android.features.repos.ui.screens.repo.RepoScreen
import com.keygenqt.viewer.services.AppHttpClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * [ViewModel] for screen [RepoScreen]
 */
@HiltViewModel
class RepoViewModel @Inject constructor(
    private val client: AppHttpClient,
    private val dataService: AppDataService,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    /**
     * Repo id
     */
    val id: String = savedStateHandle[ReposNavRoute.repo.default.argument0] ?: ""

    /**
     * Repo url for update
     */
    val url: String = savedStateHandle[ReposNavRoute.repo.default.argument1] ?: ""

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
     * Listen repo model
     */
    val repo = dataService.getRepoModelById(id).distinctUntilChanged()

    init {
        updateRepo()
    }

    /**
     * Query update repo
     */
    fun updateRepo() {
        viewModelScope.launch {
            _error.value = null
            _loading.value = true
            try {
                client.get.repo(url = url).let {
                    dataService.withTransaction<RepoModelDataService> {
                        updateRepoModel(it.toModel())
                    }
                }
            } catch (ex: Exception) {
                _error.value = ex.localizedMessage ?: ""
            }
            _loading.value = false
        }
    }
}
