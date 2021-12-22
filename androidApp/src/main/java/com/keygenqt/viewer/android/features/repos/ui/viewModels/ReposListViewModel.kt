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
package com.keygenqt.viewer.android.features.repos.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.keygenqt.viewer.android.data.models.RepoModel
import com.keygenqt.viewer.android.data.paging.ReposRemoteMediator
import com.keygenqt.viewer.android.data.preferences.BasePreferences
import com.keygenqt.viewer.android.features.repos.ui.screens.reposList.ReposListScreen
import com.keygenqt.viewer.android.services.apiService.AppApiService
import com.keygenqt.viewer.android.services.dataService.AppDataService
import com.keygenqt.viewer.android.utils.ConstantsPaging
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * [ViewModel] for screen [ReposListScreen]
 */
@HiltViewModel
class ReposListViewModel @Inject constructor(
    private val apiService: AppApiService,
    private val dataService: AppDataService,
    private val preferences: BasePreferences,
) : ViewModel() {

    /**
     * Is sort desc
     */
    private val _isSortDescListRepo: MutableStateFlow<Boolean> =
        MutableStateFlow(preferences.isSortDescListRepos)

    /**
     * StateFlow is sort desc
     */
    val isSortDescListRepo: StateFlow<Boolean> get() = _isSortDescListRepo.asStateFlow()

    /**
     * Paging remote mediator [RepoModel]
     */
    @OptIn(ExperimentalPagingApi::class)
    val listRepo: Flow<PagingData<RepoModel>> = Pager(
        config = PagingConfig(pageSize = ConstantsPaging.PAGE_LIMIT),
        remoteMediator = ReposRemoteMediator(apiService, dataService, preferences)
    ) {
        dataService.pagingSourceRepoModels()
    }.flow.cachedIn(viewModelScope)

    /**
     * Toggle type sort
     */
    fun sortToggle() {
        with(preferences) {
            isSortDescListRepos = !isSortDescListRepos
            _isSortDescListRepo.value = isSortDescListRepos
        }
    }
}
