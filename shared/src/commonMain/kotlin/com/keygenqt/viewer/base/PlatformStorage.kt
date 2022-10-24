package com.keygenqt.viewer.base

expect class PlatformStorageImpl

interface PlatformStorage {
    fun getInt(key: String): Int
    fun setInt(key: String, value: Int)
    fun getBool(key: String): Boolean
    fun setBool(key: String, value: Boolean)
    fun getStr(key: String): String?
    fun setStr(key: String, value: String)
    fun clearCache()
}

expect fun getPlatformStorage(impl: PlatformStorageImpl): PlatformStorage