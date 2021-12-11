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
package com.keygenqt.viewer.android.base.viewModel

sealed class ViewModelState {

    /**
     * Start view model
     */
    object Start : ViewModelState()

    /**
     * Stop state
     */
    object Stop : ViewModelState()

    /**
     * Action state
     */
    object Action : ViewModelState()

    /**
     * Error state with value error
     */
    data class Error(val message: String) : ViewModelState()

    /**
     * Success state with data
     */
    data class Success<T>(val data: T) : ViewModelState()

    /**
     * Check is Start
     */
    fun isStart(): Boolean {
        return this is Start
    }

    /**
     * Check is not Start
     */
    fun isNotStart(): Boolean {
        return this !is Start
    }

    /**
     * Check is Stop
     */
    fun isStop(): Boolean {
        return this is Stop
    }

    /**
     * Check is not Stop
     */
    fun isNotStop(): Boolean {
        return this !is Stop
    }

    /**
     * Check is Action
     */
    fun isAction(): Boolean {
        return this is Action
    }

    /**
     * Check is not Action
     */
    fun isNotAction(): Boolean {
        return this !is Action
    }

    /**
     * Check is Error
     */
    fun isError(): Boolean {
        return this is Error
    }

    /**
     * Check is not Error
     */
    fun isNotError(): Boolean {
        return this !is Error
    }

    /**
     * Check is Success
     */
    fun isSuccess(): Boolean {
        return this is Success<*>
    }

    /**
     * Check is not Success
     */
    fun isNotSuccess(): Boolean {
        return this !is Success<*>
    }

    /**
     * Get Success data
     */
    inline fun <reified T> getSuccessData(): T? {
        return (this as? Success<*>)?.data?.let {
            if (it is T) {
                it
            } else {
                null
            }
        }
    }

    /**
     * Get error message
     */
    fun getErrorMessage(): String? {
        return (this as? Error)?.message
    }
}
