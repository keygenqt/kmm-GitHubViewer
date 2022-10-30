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
package com.keygenqt.viewer.android.features.followers.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.keygenqt.viewer.android.data.models.FollowerModel
import com.keygenqt.viewer.android.data.paging.FollowersRemoteMediator
import com.keygenqt.viewer.android.data.services.AppDataService
import com.keygenqt.viewer.android.features.followers.ui.screens.followers.FollowersScreen
import com.keygenqt.viewer.data.storage.CrossStorage
import com.keygenqt.viewer.services.AppHttpClient
import com.keygenqt.viewer.utils.AppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * [ViewModel] for screen [FollowersScreen]
 */
@HiltViewModel
class FollowersViewModel @Inject constructor(
    private val client: AppHttpClient,
    private val dataService: AppDataService,
    private val storage: CrossStorage,
) : ViewModel() {

    /**
     * Paging remote mediator [FollowerModel]
     */
    @OptIn(ExperimentalPagingApi::class)
    val listFollowers: Flow<PagingData<FollowerModel>> = Pager(
        config = PagingConfig(pageSize = AppConstants.APP.PAGE_LIMIT),
        remoteMediator = FollowersRemoteMediator(client, dataService, storage)
    ) {
        dataService.pagingSourceFollowerModels()
    }.flow.cachedIn(viewModelScope)
}
