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
package com.keygenqt.viewer.data.storage

import android.annotation.SuppressLint
import androidx.security.crypto.EncryptedSharedPreferences

actual typealias PlatformStorage = EncryptedSharedPreferences

actual fun getStorage(storage: PlatformStorage): IStorage = AndroidPlatformStorage(storage)

@SuppressLint("CommitPrefEdits")
class AndroidPlatformStorage(
    private val storage: PlatformStorage
) : IStorage {

    override fun getInt(key: String): Int = storage
        .getInt(key, 0)

    override fun setInt(key: String, value: Int) = storage
        .edit()
        .putInt(key, value)
        .apply()

    override fun getBool(key: String): Boolean = storage
        .getBoolean(key, false)

    override fun setBool(key: String, value: Boolean) = storage
        .edit()
        .putBoolean(key, value)
        .apply()

    override fun getStr(key: String): String? = storage
        .getString(key, "")

    override fun setStr(key: String, value: String) = storage
        .edit()
        .putString(key, value)
        .apply()

    override fun clearCache() = storage
        .edit()
        .clear()
        .apply()
}
