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
import com.keygenqt.viewer.android.data.models.FollowerModel
import com.keygenqt.viewer.android.data.models.toModel
import com.keygenqt.viewer.android.data.services.AppDataService
import com.keygenqt.viewer.android.data.services.impl.FollowerModelDataService
import com.keygenqt.viewer.android.extensions.withTransaction
import com.keygenqt.viewer.android.utils.ConstantsPaging.CACHE_TIMEOUT
import com.keygenqt.viewer.data.storage.CrossStorage
import com.keygenqt.viewer.services.AppHttpClient
import kotlin.math.roundToInt

@ExperimentalPagingApi
class FollowersRemoteMediator(
    private val client: AppHttpClient,
    private val dataService: AppDataService,
    private val storage: CrossStorage,
) : RemoteMediator<Int, FollowerModel>() {

    companion object {
        private var sizeList: Int = 0
    }

    override suspend fun initialize(): InitializeAction {
        // set count cache
        dataService.withTransaction<FollowerModelDataService> {
            sizeList = countFollowerModel()
        }
        // Refresh once per hour
        return if (System.currentTimeMillis() - storage.lastUpdateListFollowers >= CACHE_TIMEOUT) {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        } else {
            InitializeAction.SKIP_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, FollowerModel>
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

            val models = client.get.followers(
                page = loadPage,
            )

            // save data
            dataService.withTransaction<FollowerModelDataService> {
                if (loadType == LoadType.REFRESH) {
                    // change update timer
                    storage.lastUpdateListFollowers = (System.currentTimeMillis() / 1000).toInt()
                    // clear data
                    clearFollowerModel()
                }
                insertFollowerModel(*models.map { it.toModel() }.toTypedArray())
                // add items count
                sizeList = countFollowerModel()
            }

            MediatorResult.Success(
                endOfPaginationReached = models.isEmpty()
            )
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}
