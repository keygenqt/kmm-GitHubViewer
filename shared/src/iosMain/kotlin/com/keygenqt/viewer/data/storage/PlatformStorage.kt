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

    override fun getLong(key: String) =
        storage.integerForKey(key)

    override fun setLong(key: String, value: Long) =
        storage.setInteger(value, key)

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