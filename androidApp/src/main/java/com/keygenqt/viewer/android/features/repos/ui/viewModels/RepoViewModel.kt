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
import com.keygenqt.requests.ResponseStates
import com.keygenqt.requests.success
import com.keygenqt.viewer.android.base.exceptions.errorHandlerStates
import com.keygenqt.viewer.android.extensions.withTransaction
import com.keygenqt.viewer.android.features.repos.navigation.route.ReposNavRoute
import com.keygenqt.viewer.android.features.repos.ui.screens.repo.RepoScreen
import com.keygenqt.viewer.android.services.apiService.AppApiService
import com.keygenqt.viewer.android.services.dataService.AppDataService
import com.keygenqt.viewer.android.services.dataService.impl.RepoModelDataService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

/**
 * [ViewModel] for screen [RepoScreen]
 */
@HiltViewModel
class RepoViewModel @Inject constructor(
    private val apiService: AppApiService,
    private val dataService: AppDataService,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    /**
     * State actions
     */
    val query1 = ResponseStates(this, ::errorHandlerStates)

    /**
     * Repo id
     */
    val id: String = savedStateHandle.get(ReposNavRoute.repo.default.argument0) ?: ""

    /**
     * Repo url for update
     */
    val url: String = savedStateHandle.get(ReposNavRoute.repo.default.argument1) ?: ""

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
        query1.queryLaunch {
            apiService.getRepo(url).success {
                dataService.withTransaction<RepoModelDataService> {
                    updateRepoModel(it)
                }
            }
        }
    }
}
