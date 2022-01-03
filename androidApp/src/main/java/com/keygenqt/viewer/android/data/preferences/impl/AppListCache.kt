/*
 * Copyright 2021 Vitaliy Zarubin
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
        IS_SORT_DESC_LIST_REPOS,
        LAST_UPDATE_LIST_REPOS,
        LAST_UPDATE_LIST_FOLLOWERS,
    }

    /**
     * Performed when the user logs out
     */
    override fun clearCacheAfterLogout() {
        isSortDescListRepos = true
        lastUpdateListRepos = 0L
        lastUpdateListFollowers = 0L
    }

    /**
     * Saving sort list
     */
    var isSortDescListRepos: Boolean
        get() = p.getBoolean(KEYS.IS_SORT_DESC_LIST_REPOS.name, true)
        set(value) = p.edit().putBoolean(KEYS.IS_SORT_DESC_LIST_REPOS.name, value).apply()

    /**
     * Saving list update data
     */
    var lastUpdateListRepos: Long
        get() = p.getLong(KEYS.LAST_UPDATE_LIST_REPOS.name, 0L)
        set(value) = p.edit().putLong(KEYS.LAST_UPDATE_LIST_REPOS.name, value).apply()

    /**
     * Saving list update data
     */
    var lastUpdateListFollowers: Long
        get() = p.getLong(KEYS.LAST_UPDATE_LIST_FOLLOWERS.name, 0L)
        set(value) = p.edit().putLong(KEYS.LAST_UPDATE_LIST_FOLLOWERS.name, value).apply()
}
