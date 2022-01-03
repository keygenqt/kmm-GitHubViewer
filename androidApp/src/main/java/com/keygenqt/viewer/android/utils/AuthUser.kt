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
package com.keygenqt.viewer.android.utils

import com.keygenqt.viewer.android.data.models.SecurityModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

object AuthUser {

    var jobAuth: Job? = null
    var jobRefresh: Job? = null

    /**
     * For listen and relations with db
     */
    private val STATE: MutableSharedFlow<AuthUser> = MutableSharedFlow()

    /**
     * For listen refresh and relations with db
     */
    private val STATE_REFRESH: MutableSharedFlow<AuthUser> = MutableSharedFlow()

    /**
     * Auth token
     */
    private var DATA: SecurityModel? = null

    /**
     * Get value for [DATA]
     */
    val data: SecurityModel? get() = DATA

    /**
     * Get user is login
     */
    val isLogin: Boolean get() = DATA != null

    /**
     * Set tokens
     */
    suspend fun login(model: SecurityModel) {
        DATA = model
        STATE.emit(this)
    }

    /**
     * Clear tokens
     */
    suspend fun logout() {
        DATA = null
        STATE.emit(this)
    }

    /**
     * Refresh model
     */
    suspend fun refresh(model: SecurityModel) {
        DATA = model
        STATE_REFRESH.emit(this)
    }

    /**
     * Auth user collect
     */
    suspend fun singleCollectAuth(action: suspend (value: AuthUser) -> Unit) {
        jobAuth?.cancel()
        coroutineScope {
            jobAuth = launch {
                STATE.asSharedFlow().collect {
                    action.invoke(it)
                }
            }
        }
    }

    /**
     * Refresh user collect
     */
    suspend fun singleCollectRefresh(action: suspend (value: SecurityModel) -> Unit) {
        jobRefresh?.cancel()
        coroutineScope {
            jobRefresh = launch {
                STATE_REFRESH.asSharedFlow().collect {
                    it.DATA?.let { model ->
                        action.invoke(model)
                    }
                }
            }
        }
    }
}
