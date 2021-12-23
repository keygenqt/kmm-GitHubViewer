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
package com.keygenqt.viewer.android.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.keygenqt.viewer.android.data.converters.ListConverter
import com.keygenqt.viewer.android.data.converters.LocalDateTimeConverter
import com.keygenqt.viewer.android.data.converters.MapConverter
import com.keygenqt.viewer.android.data.converters.RepoVisibilityConverter
import com.keygenqt.viewer.android.data.dao.FollowerModelDao
import com.keygenqt.viewer.android.data.dao.RepoModelDao
import com.keygenqt.viewer.android.data.dao.UserModelDao
import com.keygenqt.viewer.android.data.models.FollowerModel
import com.keygenqt.viewer.android.data.models.RepoModel
import com.keygenqt.viewer.android.data.models.UserModel

/**
 * Database configuration [RoomDatabase]
 */
@Database(
    entities = [
        UserModel::class,
        RepoModel::class,
        FollowerModel::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    value = [
        MapConverter::class,
        ListConverter::class,
        LocalDateTimeConverter::class,
        RepoVisibilityConverter::class,
    ]
)
abstract class AppDatabase : RoomDatabase() {
    /**
     * Dao for model [UserModel]
     */
    abstract fun userModelDao(): UserModelDao

    /**
     * Dao for model [RepoModel]
     */
    abstract fun repoModelDao(): RepoModelDao

    /**
     * Dao for model [FollowerModel]
     */
    abstract fun followerModelDao(): FollowerModelDao
}
