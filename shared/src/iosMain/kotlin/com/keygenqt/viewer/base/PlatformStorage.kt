package com.keygenqt.viewer.base

import platform.Foundation.NSUserDefaults

actual typealias PlatformStorageImpl = NSUserDefaults

actual fun getPlatformStorage(impl: PlatformStorageImpl): PlatformStorage = IOSPlatformStorage(impl)

class IOSPlatformStorage(
    private val impl: PlatformStorageImpl
) : PlatformStorage {

    override fun getInt(key: String) =
        impl.integerForKey(key).toInt()

    override fun setInt(key: String, value: Int) =
        impl.setInteger(value.toLong(), key)

    override fun getBool(key: String) =
        impl.boolForKey(key)

    override fun setBool(key: String, value: Boolean) =
        impl.setBool(value, key)

    override fun getStr(key: String): String? =
        impl.stringForKey(key)

    override fun setStr(key: String, value: String) =
        impl.setObject(value, key)

    override fun clearCache() = impl
        .dictionaryRepresentation()
        .forEach {
            NSUserDefaults.standardUserDefaults.removeObjectForKey(it.key.toString())
        }
}