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
import com.keygenqt.viewer.android.data.dao.RepoModelDao
import com.keygenqt.viewer.android.data.models.RepoModel
import com.keygenqt.viewer.android.interfaces.IAppDatabase
import kotlinx.coroutines.flow.Flow

/**
 * Service part for work with model [RepoModel]
 */
interface RepoModelDataService : IAppDatabase {

    /**
     * Base room db
     */
    override val db: AppDatabase

    /**
     * Doa model [RepoModel]
     */
    private val dao: RepoModelDao get() = db.repoModelDao()

    /**
     * Performed when the user logs out
     */
    override suspend fun clearCacheAfterLogout() {
        clearRepoModel()
    }

    /**
     * Fun for insert models
     */
    suspend fun insertRepoModel(vararg models: RepoModel) {
        dao.insertModels(*models)
    }

    /**
     * Fun for update model
     */
    suspend fun updateRepoModel(model: RepoModel) {
        dao.updateModel(model)
    }

    /**
     * Get [PagingSource] for paging list
     */
    fun pagingSourceRepoModels(): PagingSource<Int, RepoModel> {
        return dao.pagingSource()
    }

    /**
     * Get [Flow] model by id
     */
    fun getRepoModelById(id: String): Flow<RepoModel> {
        return dao.getModel(id)
    }

    /**
     * Remove all models
     */
    suspend fun clearRepoModel() {
        dao.clear()
    }

    /**
     * Count all models
     */
    suspend fun countRepoModel(): Int {
        return dao.count()
    }
}
