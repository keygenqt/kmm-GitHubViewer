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

    override fun getLong(key: String): Long = storage
        .getLong(key, 0L)

    override fun setLong(key: String, value: Long) = storage
        .edit()
        .putLong(key, value)
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

