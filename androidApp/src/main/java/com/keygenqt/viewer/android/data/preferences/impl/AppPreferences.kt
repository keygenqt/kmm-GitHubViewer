package com.keygenqt.viewer.android.data.preferences.impl

import android.content.SharedPreferences
import com.keygenqt.viewer.android.interfaces.IAppPreferences
import timber.log.Timber

/**
 * Common service shared preference
 *
 * @author Vitaliy Zarubin
 */
interface AppPreferences : IAppPreferences {

    /**
     * Store private, primitive data in key-value pairs [SharedPreferences]
     */
    override val p: SharedPreferences

    /**
     * We put the keys in enum
     */
    enum class KEYS {
        IS_FIRST_OPEN,
    }

    /**
     * Performed when the user logs out
     */
    override fun clearCacheAfterLogout() {
        Timber.d("Clear cache: AppPreferences")
    }

    /**
     * An example of a possible variable
     */
    var isFirstOpen: Boolean
        get() = p.getBoolean(KEYS.IS_FIRST_OPEN.name, true)
        set(value) = p.edit().putBoolean(KEYS.IS_FIRST_OPEN.name, value).apply()
}
