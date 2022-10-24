package com.keygenqt.viewer.base

import android.annotation.SuppressLint
import androidx.security.crypto.EncryptedSharedPreferences

actual typealias PlatformStorageImpl = EncryptedSharedPreferences

actual fun getPlatformStorage(impl: PlatformStorageImpl): PlatformStorage =
    AndroidPlatformStorage(impl)

@SuppressLint("CommitPrefEdits")
class AndroidPlatformStorage(
    private val impl: PlatformStorageImpl
) : PlatformStorage {

    override fun getInt(key: String): Int = impl
        .getInt(key, 0)

    override fun setInt(key: String, value: Int) = impl
        .edit()
        .putInt(key, value)
        .apply()

    override fun getBool(key: String): Boolean = impl
        .getBoolean(key, false)

    override fun setBool(key: String, value: Boolean) = impl
        .edit()
        .putBoolean(key, value)
        .apply()

    override fun getStr(key: String): String? = impl
        .getString(key, "")

    override fun setStr(key: String, value: String) = impl
        .edit()
        .putString(key, value)
        .apply()

    override fun clearCache() = impl
        .edit()
        .clear()
        .apply()
}

