package com.keygenqt.viewer.base

class StorageKMM(val impl: PlatformStorageImpl) {

    private val storage: PlatformStorage = getPlatformStorage(impl)

    /**
     * We put the keys in enum
     */
    enum class KEYS {
        IS_ONBOARDING_DONE,
    }

    /**
     * Performed when the user logs out
     */
    fun clearCache() {
        storage.clearCache()
    }

    /**
     * An example of a possible variable
     */
    var isOnboardingDone: Boolean
        get() = storage.getBool(KEYS.IS_ONBOARDING_DONE.name)
        set(value) = storage.setBool(KEYS.IS_ONBOARDING_DONE.name, value)
}