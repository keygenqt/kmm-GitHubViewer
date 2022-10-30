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

import org.w3c.dom.Storage

@JsExport
class StorageClass : Storage()

actual typealias PlatformStorage = StorageClass

@JsExport
actual fun getStorage(storage: PlatformStorage): IStorage = JSPlatformStorage(storage)

@JsExport
class JSPlatformStorage(
    private val storage: StorageClass
) : IStorage {

    override fun getInt(key: String) =
        storage.getItem(key)?.toIntOrNull() ?: 0

    override fun setInt(key: String, value: Int) =
        storage.setItem(key, value.toString())

    override fun getBool(key: String) =
        storage.getItem(key)?.toBoolean() ?: false

    override fun setBool(key: String, value: Boolean) =
        storage.setItem(key, value.toString())

    override fun getStr(key: String): String? =
        storage.getItem(key)

    override fun setStr(key: String, value: String) =
        storage.setItem(key, value)

    override fun clearCache() =
        storage.clear()
}
