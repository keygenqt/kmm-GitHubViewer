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
package com.keygenqt.viewer.android.services.dataService.impl

import androidx.paging.PagingSource
import com.keygenqt.viewer.android.data.AppDatabase
import com.keygenqt.viewer.android.data.dao.FollowerModelDao
import com.keygenqt.viewer.android.data.models.FollowerModel
import com.keygenqt.viewer.android.interfaces.IAppDatabase
import kotlinx.coroutines.flow.Flow

/**
 * Service part for work with model [FollowerModel]
 */
interface FollowerModelDataService : IAppDatabase {

    /**
     * Base room db
     */
    override val db: AppDatabase

    /**
     * Doa model [FollowerModel]
     */
    private val dao: FollowerModelDao get() = db.followerModelDao()

    /**
     * Performed when the user logs out
     */
    override suspend fun clearCacheAfterLogout() {
        clearFollowerModel()
    }

    /**
     * Fun for insert models
     */
    suspend fun insertFollowerModel(vararg models: FollowerModel) {
        dao.insertModels(*models)
    }

    /**
     * Get [PagingSource] for paging list
     */
    fun pagingSourceFollowerModels(): PagingSource<Int, FollowerModel> {
        return dao.pagingSource()
    }

    /**
     * Remove all models
     */
    suspend fun clearFollowerModel() {
        dao.clear()
    }

    /**
     * Count all models
     */
    suspend fun countFollowerModel(): Int {
        return dao.count()
    }
}
