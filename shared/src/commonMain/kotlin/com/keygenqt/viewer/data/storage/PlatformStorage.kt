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