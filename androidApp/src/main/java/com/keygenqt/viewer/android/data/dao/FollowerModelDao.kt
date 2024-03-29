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
package com.keygenqt.viewer.android.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.keygenqt.viewer.android.data.models.FollowerModel

/**
 * Dao for model [FollowerModel]
 */
@Dao
interface FollowerModelDao {

    @Query("SELECT * FROM FollowerModel")
    fun pagingSource(): PagingSource<Int, FollowerModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertModels(vararg models: FollowerModel)

    @Query("DELETE FROM FollowerModel")
    suspend fun clear()

    @Query("SELECT COUNT(*) FROM FollowerModel")
    suspend fun count(): Int
}
