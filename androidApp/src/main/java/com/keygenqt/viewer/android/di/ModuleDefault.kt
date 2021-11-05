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
package com.keygenqt.viewer.android.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.keygenqt.viewer.android.base.AppDatabaseQualifier
import com.keygenqt.viewer.android.base.AppDatabaseSecurityQualifier
import com.keygenqt.viewer.android.data.AppDatabase
import com.keygenqt.viewer.android.data.AppSecurityDatabase
import com.keygenqt.viewer.android.data.migrations.Migrations_1_2
import com.keygenqt.viewer.android.services.dataService.AppDataService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

/**
 * Dagger Module base for app
 */
@Module
@InstallIn(SingletonComponent::class)
object ModuleDefault {

    /**
     * Base encryption key
     */
    private const val password = "AnTjRKeqmTi7OyTfdein"

    /**
     * List with migrations for the database
     */
    private val migrations = listOf(
        Migrations_1_2
    )

    /**
     * Database with encryption and migrations
     */
    @Provides
    @AppDatabaseSecurityQualifier
    fun provideCoreSecurityDatabase(@ApplicationContext context: Context): AppSecurityDatabase {
        val passphrase = SQLiteDatabase.getBytes(password.toCharArray())
        val factory = SupportFactory(passphrase)
        val builder = Room
            .databaseBuilder(
                context,
                AppSecurityDatabase::class.java,
                "${ModuleDefault::class.qualifiedName}.security.db"
            )

        migrations.forEach {
            builder.addMigrations(it)
        }

        return builder.fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .allowMainThreadQueries()
            .build()
    }

    /**
     * A database that doesn't need migrations and encryption
     */
    @Provides
    @AppDatabaseQualifier
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "${ModuleDefault::class.qualifiedName}.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    /**
     * Database management service
     */
    @Provides
    fun provideUsersDataService(
        @AppDatabaseQualifier db: AppDatabase,
        @AppDatabaseSecurityQualifier dbSecurity: AppSecurityDatabase,
    ) = AppDataService(db, dbSecurity)
}
