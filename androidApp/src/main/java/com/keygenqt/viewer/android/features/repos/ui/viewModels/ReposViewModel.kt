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
import com.keygenqt.response.extensions.success
import com.keygenqt.viewer.android.base.viewModel.ViewModelStates
import com.keygenqt.viewer.android.extensions.withTransaction
import com.keygenqt.viewer.android.services.apiService.AppApiService
import com.keygenqt.viewer.android.services.dataService.AppDataService
import com.keygenqt.viewer.android.services.dataService.impl.RepoModelDataService
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

/**
 * [ViewModel] for feature
 */
@HiltViewModel
class ReposViewModel @Inject constructor(
    private val apiService: AppApiService,
    private val dataService: AppDataService
) : ViewModelStates() {

    /**
     * Start loading data user
     */
    fun startLoading() {
        queryLaunch {
            apiService.getUserRepos().success { models ->
                // @todo
                models.forEach { model ->
                    Timber.e(model.toString())
                }
                dataService.withTransaction<RepoModelDataService> {
                    clearRepoModel()
                    insertRepoModel(*models.toTypedArray())
                }
            }
        }
    }
}
