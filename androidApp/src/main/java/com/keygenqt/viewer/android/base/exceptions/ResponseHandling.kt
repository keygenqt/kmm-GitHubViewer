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
package com.keygenqt.viewer.android.base.exceptions

import android.content.Context
import com.keygenqt.response.LocalTryExecuteWithResponse
import com.keygenqt.response.LocalTryExecuteWithResponse.executeWithResponse
import com.keygenqt.response.ResponseResult
import com.keygenqt.response.extensions.isSucceeded
import com.keygenqt.response.extensions.success
import com.keygenqt.viewer.android.BuildConfig
import com.keygenqt.viewer.android.R
import com.keygenqt.viewer.android.data.mappers.toModel
import com.keygenqt.viewer.android.data.requests.RefreshTokenRequest
import com.keygenqt.viewer.android.services.api.impl.ApiRefreshToken
import com.keygenqt.viewer.android.utils.AuthUser
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.reflect.full.createInstance

/**
 * Job single collect response errors
 */
var jobSingleCollectResponseErrors: Job? = null

/**
 * Single collect response errors
 */
suspend fun singleCollectResponseErrors(
    context: Context,
    action: suspend (exception: Exception, message: String) -> Unit
) {
    jobSingleCollectResponseErrors?.cancel()
    coroutineScope {
        jobSingleCollectResponseErrors = launch {
            LocalTryExecuteWithResponse.current.collect {
                with(context) {
                    val message = when (it) {
                        is ResponseException -> getString(it.resId)
                        is DataException -> {
                            when (it.message) {
                                "Bad credentials" -> getString(R.string.error_bad_credentials)
                                "Something wrong" -> getString(R.string.error_something_wrong)
                                else -> it.message
                            }
                        }
                        else -> it.message
                    }
                    action.invoke(it, message ?: getString(R.string.error_something_wrong))
                }
            }
        }
    }
}

/**
 * Code to class
 */
fun Int.toResponseException(): ResponseException {
    return ResponseException::class.sealedSubclasses
        .map { it.objectInstance ?: it.createInstance() }
        .firstOrNull { it.code == this }
        ?: ResponseException.ExceptionUnknown(code = this)
}

/**
 * [executeWithResponse] + refresh token
 */
suspend inline fun <T> executeRefreshToken(
    api: ApiRefreshToken,
    emit: Boolean = true,
    body: () -> T
): ResponseResult<T> {
    return try {
        ResponseResult.Success(body.invoke())
    } catch (e: Exception) {
        // get refresh
        val refreshToken = AuthUser.data?.refreshToken
        // check
        if (e is ResponseException.TokenExpired && refreshToken != null) {
            // query refresh
            val response = executeWithResponse(emit = false) {
                api.refreshToken(
                    request = RefreshTokenRequest(
                        refresh_token = refreshToken,
                        grant_type = "refresh_token",
                        client_secret = BuildConfig.GITHUB_CLIENT_SECRET,
                        client_id = BuildConfig.GITHUB_CLIENT_ID,
                    )
                ).body()!!.toModel()
            }.success {
                AuthUser.refresh(it)
            }
            // re query
            if (response.isSucceeded) {
                executeWithResponse { body.invoke() }
            } else {
                if (emit) {
                    LocalTryExecuteWithResponse.tryEmit(e)
                }
                ResponseResult.Error(e)
            }
        } else {
            if (emit) {
                LocalTryExecuteWithResponse.tryEmit(e)
            }
            ResponseResult.Error(e)
        }
    }
}
