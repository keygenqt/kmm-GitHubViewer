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
package com.keygenqt.viewer.android.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.keygenqt.viewer.android.extensions.checkValidJson
import com.keygenqt.viewer.android.utils.AuthUser
import com.keygenqt.viewer.android.utils.ConstantsApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Retrofit
import timber.log.Timber

/**
 * Dagger Module with Retrofit
 */
@Module
@InstallIn(SingletonComponent::class)
object HttpClientModule {

    /**
     * Builder of the [Json] instance provided by `Json { ... }` factory function.
     */
    @OptIn(ExperimentalSerializationApi::class)
    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = false
        explicitNulls = false
    }

    /**
     * Factory for [calls][Call], which can be used to send HTTP requests and read their responses.
     */
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor { message ->
                    try {
                        Timber.d(JSONObject(message).toString(2))
                    } catch (e: Exception) {
                        try {
                            Timber.d(JSONArray(message).toString(2))
                        } catch (e: Exception) {
                            Timber.d(message)
                        }
                    }
                }.apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .addInterceptor {
                it.proceed(it.request()).checkValidJson()
            }
            .addInterceptor {
                val original = it.request()
                val request = original.newBuilder().apply {
                    AuthUser.data?.accessToken?.let { token ->
                        header("Authorization", "token $token")
                        header("Accept", "application/vnd.github.v3+json")
                    }
                }
                    .method(original.method, original.body)
                    .build()
                Timber.d(request.headers.toMultimap().toString())
                it.proceed(request)
            }
            .build()
    }

    /**
     * Retrofit adapts a Java interface to HTTP calls by using annotations on the declared methods to define how requests are made.
     */
    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(ConstantsApp.API_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }
}
