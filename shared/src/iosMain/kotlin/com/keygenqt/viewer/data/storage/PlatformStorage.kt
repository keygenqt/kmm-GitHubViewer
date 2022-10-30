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

import platform.Foundation.NSUserDefaults

actual typealias PlatformStorage = NSUserDefaults

actual fun getStorage(storage: PlatformStorage): IStorage = IOSPlatformStorage(storage)

class IOSPlatformStorage(
    private val storage: PlatformStorage
) : IStorage {

    override fun getInt(key: String) =
        storage.integerForKey(key).toInt()

    override fun setInt(key: String, value: Int) =
        storage.setInteger(value.toLong(), key)

    override fun getBool(key: String) =
        storage.boolForKey(key)

    override fun setBool(key: String, value: Boolean) =
        storage.setBool(value, key)

    override fun getStr(key: String): String? =
        storage.stringForKey(key)

    override fun setStr(key: String, value: String) =
        storage.setObject(value, key)

    override fun clearCache() = storage
        .dictionaryRepresentation()
        .forEach {
            NSUserDefaults.standardUserDefaults.removeObjectForKey(it.key.toString())
        }
}
