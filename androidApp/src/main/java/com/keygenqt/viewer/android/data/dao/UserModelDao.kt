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

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.keygenqt.viewer.android.data.models.UserModel
import kotlinx.coroutines.flow.Flow

/**
 * Dao for model [UserModel]
 */
@Dao
interface UserModelDao {

    @Query("SELECT * FROM UserModel LIMIT 1")
    fun getModel(): Flow<UserModel?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertModels(vararg models: UserModel)

    @Query("DELETE FROM UserModel")
    suspend fun clear()

    @Query("SELECT COUNT(*) FROM UserModel")
    fun count(): Int
}
