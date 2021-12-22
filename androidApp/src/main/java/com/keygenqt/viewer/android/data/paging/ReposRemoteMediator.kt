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
package com.keygenqt.viewer.android.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.keygenqt.response.extensions.error
import com.keygenqt.response.extensions.isEmpty
import com.keygenqt.response.extensions.isError
import com.keygenqt.response.extensions.success
import com.keygenqt.viewer.android.data.models.RepoModel
import com.keygenqt.viewer.android.data.preferences.BasePreferences
import com.keygenqt.viewer.android.extensions.withTransaction
import com.keygenqt.viewer.android.services.apiService.AppApiService
import com.keygenqt.viewer.android.services.dataService.AppDataService
import com.keygenqt.viewer.android.services.dataService.impl.RepoModelDataService
import timber.log.Timber
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.roundToInt

@ExperimentalPagingApi
class ReposRemoteMediator(
    private val apiService: AppApiService,
    private val dataService: AppDataService,
    private val preferences: BasePreferences,
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
        return if (System.currentTimeMillis() - preferences.lastUpdateListRepos >= TimeUnit.HOURS.toMillis(1)) {
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
                LoadType.REFRESH -> { sizeList = 0; 1 }
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> (sizeList / state.config.pageSize.toFloat())
                    .roundToInt()
                    .plus(1)
            }

            val response = apiService.getUserRepos(
                page = loadPage,
                isSortDesc = preferences.isSortDescListRepos
            )
                .success { models ->
                    // save data
                    dataService.withTransaction<RepoModelDataService> {
                        if (loadType == LoadType.REFRESH) {
                            // change update timer
                            preferences.lastUpdateListRepos = System.currentTimeMillis()
                            // clear data
                            clearRepoModel()
                        }
                        insertRepoModel(*models.toTypedArray())
                        // add items count
                        sizeList = countRepoModel()
                    }
                }.error {
                    throw it
                }

            MediatorResult.Success(
                endOfPaginationReached = response.isError || response.isEmpty
            )
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}
