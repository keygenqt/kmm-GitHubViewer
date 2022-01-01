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
package com.keygenqt.viewer.android.services.dataService.impl

import com.keygenqt.viewer.android.data.AppDatabase
import com.keygenqt.viewer.android.data.dao.UserModelDao
import com.keygenqt.viewer.android.data.models.UserModel
import com.keygenqt.viewer.android.interfaces.IAppDatabase
import kotlinx.coroutines.flow.Flow

/**
 * Service part for work with model [UserModel]
 */
interface UserModelDataService : IAppDatabase {

    /**
     * Base room db
     */
    override val db: AppDatabase

    /**
     * Doa model [UserModel]
     */
    private val dao: UserModelDao get() = db.userModelDao()

    /**
     * Performed when the user logs out
     */
    override suspend fun clearCacheAfterLogout() {
        clearUserModel()
    }

    /**
     * Fun for insert models
     */
    suspend fun insertUserModel(vararg models: UserModel) {
        dao.insertModels(*models)
    }

    /**
     * Get [Flow] model
     */
    fun getUserModel(): Flow<UserModel?> {
        return dao.getModel()
    }

    /**
     * Remove all models
     */
    suspend fun clearUserModel() {
        dao.clear()
    }
}
