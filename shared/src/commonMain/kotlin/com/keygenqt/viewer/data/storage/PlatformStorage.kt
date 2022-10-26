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

expect class PlatformStorage

interface IStorage {
    fun getInt(key: String): Int
    fun setInt(key: String, value: Int)
    fun getLong(key: String): Long
    fun setLong(key: String, value: Long)
    fun getBool(key: String): Boolean
    fun setBool(key: String, value: Boolean)
    fun getStr(key: String): String?
    fun setStr(key: String, value: String)
    fun clearCache()
}

expect fun getStorage(storage: PlatformStorage): IStorage
