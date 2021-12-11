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

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.keygenqt.viewer.android.data.preferences.BasePreferences
import com.keygenqt.viewer.android.services.api.AppApi
import com.keygenqt.viewer.android.services.apiService.AppApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

/**
 * Module Dagger responsible for api services
 */
@Module
@InstallIn(ViewModelComponent::class)
object ModuleViewModel {

    /**
     * HTTP API into a interface
     */
    @Provides
    fun provideCoreApi(retrofit: Retrofit): AppApi = retrofit.create(AppApi::class.java)

    /**
     * HTTP query service
     */
    @Provides
    fun provideUsersApiService(api: AppApi) = AppApiService(api)

    /**
     * Shared preferences
     */
    @Provides
    @ViewModelScoped
    fun provideSharedPreferences(@ApplicationContext context: Context): BasePreferences {
        return BasePreferences(
            EncryptedSharedPreferences.create(
                BasePreferences::class.java.simpleName,
                MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
                context,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        )
    }
}
