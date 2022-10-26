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
package com.keygenqt.viewer.android.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.keygenqt.viewer.android.data.models.RepoModel
import com.keygenqt.viewer.android.data.models.toModel
import com.keygenqt.viewer.android.data.services.AppDataService
import com.keygenqt.viewer.android.extensions.withTransaction
import com.keygenqt.viewer.android.data.services.impl.RepoModelDataService
import com.keygenqt.viewer.android.utils.ConstantsPaging.CACHE_TIMEOUT
import com.keygenqt.viewer.data.storage.CrossStorage
import com.keygenqt.viewer.services.AppHttpClient
import kotlin.math.roundToInt

@ExperimentalPagingApi
class ReposRemoteMediator(
    private val client: AppHttpClient,
    private val dataService: AppDataService,
    private val storage: CrossStorage,
) : RemoteMediator<Int, RepoModel>() {

    companion object {
        private var sizeList: Int = 0
    }

    override suspend fun initialize(): InitializeAction {
        // set count cache
        dataService.withTransaction<RepoModelDataService> {
            sizeList = countRepoModel()
        }
        // Refresh once per hour
        return if (System.currentTimeMillis() - storage.lastUpdateListRepos >= CACHE_TIMEOUT) {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        } else {
            InitializeAction.SKIP_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RepoModel>
    ): MediatorResult {
        return try {

            val loadPage = when (loadType) {
                LoadType.REFRESH -> {
                    sizeList = 0; 1
                }
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> (sizeList / state.config.pageSize.toFloat())
                    .roundToInt()
                    .plus(1)
            }

            val models = client.get.repos(
                page = loadPage,
                isSortASC = storage.isRepoOrder
            )

            dataService.withTransaction<RepoModelDataService> {
                if (loadType == LoadType.REFRESH) {
                    // change update timer
                    storage.lastUpdateListRepos = System.currentTimeMillis()
                    // clear data
                    clearRepoModel()
                }
                insertRepoModel(*models.map { it.toModel() }.toTypedArray())
                // add items count
                sizeList = countRepoModel()
            }

            MediatorResult.Success(
                endOfPaginationReached = models.isEmpty()
            )
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}
