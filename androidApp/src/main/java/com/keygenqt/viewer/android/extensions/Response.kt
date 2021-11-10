/**
 * Copyright © 2021 Surf. All rights reserved.
 */
package com.keygenqt.viewer.android.extensions

import com.keygenqt.viewer.android.utils.ConstantsApp
import kotlinx.coroutines.runBlocking
import retrofit2.Response

/**
 * Simulate slow internet
 */
fun <T> Response<T>.delay(enable: Boolean): Response<T> {
    if (enable) {
        runBlocking {
            kotlinx.coroutines.delay(ConstantsApp.DEBUG_DELAY)
        }
    }
    return this
}
