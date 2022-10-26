/*
 * Copyright 2022 Vitaliy Zarubin
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
package com.keygenqt.viewer.data.storage

class CrossStorage(val storage: PlatformStorage) {

    private val ps: IStorage = getStorage(storage)

    /**
     * We put the keys in enum
     */
    enum class KEYS {
        AUTH_TOKEN,
        IS_ONBOARDING_DONE,
        IS_REPO_ORDER,
        LAST_UPDATE_LIST_REPOS,
        LAST_UPDATE_LIST_FOLLOWERS,
    }

    /**
     * Clear all cache
     */
    fun clearCache() {
        ps.clearCache()
    }

    /**
     * Save token after auth
     */
    var authToken: String
        get() = ps.getStr(KEYS.AUTH_TOKEN.name) ?: ""
        set(value) = ps.setStr(KEYS.AUTH_TOKEN.name, value)

    /**
     * Is pass onboarding
     */
    var isOnboardingDone: Boolean
        get() = ps.getBool(KEYS.IS_ONBOARDING_DONE.name)
        set(value) = ps.setBool(KEYS.IS_ONBOARDING_DONE.name, value)

    /**
     * is enable ASK sort repos
     */
    var isRepoOrder: Boolean
        get() = ps.getBool(KEYS.IS_REPO_ORDER.name)
        set(value) = ps.setBool(KEYS.IS_REPO_ORDER.name, value)

    /**
     * Saving list update data
     */
    var lastUpdateListRepos: Long
        get() = ps.getLong(KEYS.LAST_UPDATE_LIST_REPOS.name)
        set(value) = ps.setLong(KEYS.LAST_UPDATE_LIST_REPOS.name, value)

    /**
     * Saving list update data
     */
    var lastUpdateListFollowers: Long
        get() = ps.getLong(KEYS.LAST_UPDATE_LIST_FOLLOWERS.name)
        set(value) = ps.setLong(KEYS.LAST_UPDATE_LIST_FOLLOWERS.name, value)
}
