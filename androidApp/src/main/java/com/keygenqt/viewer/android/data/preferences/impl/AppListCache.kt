package com.keygenqt.viewer.android.data.preferences.impl

import android.content.SharedPreferences
import com.keygenqt.viewer.android.interfaces.IAppPreferences
import timber.log.Timber

/**
 * Lists service shared preference
 *
 * @author Vitaliy Zarubin
 */
interface AppListCache : IAppPreferences {

    /**
     * Store private, primitive data in key-value pairs [SharedPreferences]
     */
    override val p: SharedPreferences

    /**
     * We put the keys in enum
     */
    enum class KEYS {
        IS_SORT_DESC_LIST_REPO,
        LAST_UPDATE_LIST_REPO,
    }

    /**
     * Performed when the user logs out
     */
    override fun clearCacheAfterLogout() {
        Timber.d("Clear cache: AppListCache")
        isSortDescListRepo = false
        lastUpdateListRepo = 0L
    }

    /**
     * Saving sort list
     */
    var isSortDescListRepo: Boolean
        get() = p.getBoolean(KEYS.IS_SORT_DESC_LIST_REPO.name, false)
        set(value) = p.edit().putBoolean(KEYS.IS_SORT_DESC_LIST_REPO.name, value).apply()

    /**
     * Saving list update data
     */
    var lastUpdateListRepo: Long
        get() = p.getLong(KEYS.LAST_UPDATE_LIST_REPO.name, 0L)
        set(value) = p.edit().putLong(KEYS.LAST_UPDATE_LIST_REPO.name, value).apply()
}
