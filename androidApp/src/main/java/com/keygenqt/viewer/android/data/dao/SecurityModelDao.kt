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

import androidx.room.*
import com.keygenqt.viewer.android.data.models.SecurityModel

/**
 * Dao for model [SecurityModel]
 */
@Dao
interface SecurityModelDao {

    @Query("SELECT * FROM SecurityModel LIMIT 1")
    fun getModel(): SecurityModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertModels(vararg models: SecurityModel)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateModels(model: SecurityModel)

    @Query("DELETE FROM SecurityModel")
    suspend fun clear()

    @Query("SELECT COUNT(*) FROM SecurityModel")
    fun count(): Int
}
