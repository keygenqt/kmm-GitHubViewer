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
package com.keygenqt.viewer.android.features.repos.ui.screens.repoUpdate

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.keygenqt.requests.ResponseComposable
import com.keygenqt.requests.ResponseState
import com.keygenqt.viewer.android.base.exceptions.ResponseException

@Composable
fun RepoUpdateQueryState1(
    state: ResponseState = ResponseState.Start,
    clear: () -> Unit = {},
    loading: () -> Unit = {},
    success: () -> Unit = {},
    error: (ResponseException) -> Unit = {},
) {
    ResponseComposable(state, clear = clear) {
        when (this) {
            is ResponseState.Action -> loading.invoke()
            is ResponseState.Success<*> -> success.invoke()
            is ResponseState.Error -> error.invoke(exception as ResponseException)
            else -> {}
        }
    }
}
