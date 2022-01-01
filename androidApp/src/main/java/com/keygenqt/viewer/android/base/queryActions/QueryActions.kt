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
package com.keygenqt.viewer.android.base.queryActions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keygenqt.response.ResponseResult
import com.keygenqt.response.extensions.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class QueryActions(val viewModel: ViewModel) {

    /**
     * Action state
     */
    private val _state: MutableStateFlow<QueryState> = MutableStateFlow(QueryState.Start)

    /**
     * [StateFlow] for [_state]
     */
    val state: StateFlow<QueryState> get() = _state.asStateFlow()

    /**
     * Launch query
     */
    fun <T> queryLaunch(
        query: suspend CoroutineScope.() -> ResponseResult<T>
    ) {
        // set loading
        _state.value = QueryState.Action
        // launch scope
        viewModel.viewModelScope.launch {
            query()
                .success(::setSuccess)
                .error(::setError)
                .errorUnknownHost(::setError)
                .errorTimeout(::setError)
                .empty { setSuccess(null) }
        }
    }

    /**
     * Set state Success
     */
    private fun <T> setSuccess(data: T?) {
        _state.value = QueryState.Success(data)
    }

    /**
     * Set state exception Error
     */
    private fun setError(exception: Exception) {
        _state.value = QueryState.Error(exception)
    }
}
