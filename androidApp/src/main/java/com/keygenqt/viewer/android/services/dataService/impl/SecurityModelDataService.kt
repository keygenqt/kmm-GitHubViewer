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

import com.keygenqt.viewer.android.data.AppSecurityDatabase
import com.keygenqt.viewer.android.data.dao.SecurityModelDao
import com.keygenqt.viewer.android.data.models.SecurityModel
import com.keygenqt.viewer.android.interfaces.IAppDatabase
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

/**
 * Service part for work with model [SecurityModel]
 */
interface SecurityModelDataService : IAppDatabase {

    /**
     * Base room db
     */
    val dbSecurity: AppSecurityDatabase

    /**
     * Doa model [SecurityModel]
     */
    private val dao: SecurityModelDao get() = dbSecurity.securityModelDao()

    /**
     * Performed when the user logs out
     */
    override fun clearCacheAfterLogout() {
        Timber.e("Clear cache: SecurityModelDataService")
    }

    /**
     * Fun for insert models
     */
    suspend fun insertSecurityModel(vararg models: SecurityModel) {
        dao.insertModels(*models)
    }

    /**
     * Get [Flow] model
     */
    fun getSecurityModel(): Flow<SecurityModel?> {
        return dao.getModel()
    }

    /**
     * Remove all models
     */
    suspend fun clearSecurityModel() {
        dao.clear()
    }

    /**
     * Check user is login
     */
    fun isLogin(): Boolean {
        return dao.count() != 0
    }
}
