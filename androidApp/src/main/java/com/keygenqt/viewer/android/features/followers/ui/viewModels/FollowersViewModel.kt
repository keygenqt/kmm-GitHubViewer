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
package com.keygenqt.viewer.android.features.followers.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.keygenqt.viewer.android.data.models.FollowerModel
import com.keygenqt.viewer.android.data.paging.FollowersRemoteMediator
import com.keygenqt.viewer.android.data.preferences.BasePreferences
import com.keygenqt.viewer.android.features.followers.ui.screens.followersMain.FollowersMainScreen
import com.keygenqt.viewer.android.services.apiService.AppApiService
import com.keygenqt.viewer.android.services.dataService.AppDataService
import com.keygenqt.viewer.android.utils.ConstantsPaging
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * [ViewModel] for screen [FollowersMainScreen]
 */
@HiltViewModel
class FollowersViewModel @Inject constructor(
    private val apiService: AppApiService,
    private val dataService: AppDataService,
    private val preferences: BasePreferences,
) : ViewModel() {

    /**
     * Paging remote mediator [FollowerModel]
     */
    @OptIn(ExperimentalPagingApi::class)
    val listFollowers: Flow<PagingData<FollowerModel>> = Pager(
        config = PagingConfig(pageSize = ConstantsPaging.PAGE_LIMIT),
        remoteMediator = FollowersRemoteMediator(apiService, dataService, preferences)
    ) {
        dataService.pagingSourceFollowerModels()
    }.flow
}
