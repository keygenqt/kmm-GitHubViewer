package com.keygenqt.viewer.android.data.preferences

import android.content.SharedPreferences
import com.keygenqt.viewer.android.data.preferences.impl.AppListCache
import com.keygenqt.viewer.android.data.preferences.impl.AppPreferences

/**
 * App service shared preference
 *
 * @author Vitaliy Zarubin
 */
class BasePreferences(override val p: SharedPreferences) :
    AppPreferences,
    AppListCache {

    /**
     * Performed when the user logs out
     */
    override fun clearCacheAfterLogout() {
        super<AppPreferences>.clearCacheAfterLogout()
        super<AppListCache>.clearCacheAfterLogout()
    }
}
