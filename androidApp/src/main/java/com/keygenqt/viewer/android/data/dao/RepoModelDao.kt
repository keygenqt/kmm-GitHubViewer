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
package com.keygenqt.viewer.android.data.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.keygenqt.viewer.android.data.models.RepoModel
import kotlinx.coroutines.flow.Flow

/**
 * Dao for model [RepoModel]
 */
@Dao
interface RepoModelDao {

    @Query("SELECT * FROM RepoModel")
    fun pagingSource(): PagingSource<Int, RepoModel>

    @Query("SELECT * FROM RepoModel")
    fun getModels(): Flow<List<RepoModel>>

    @Query("SELECT * FROM RepoModel WHERE id = :id")
    fun getModel(id: String): Flow<RepoModel>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateModel(model: RepoModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertModels(vararg models: RepoModel)

    @Query("DELETE FROM RepoModel")
    suspend fun clear()
}
