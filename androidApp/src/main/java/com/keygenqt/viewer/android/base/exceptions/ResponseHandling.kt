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

import com.keygenqt.requests.*
import com.keygenqt.requests.RequestHandler.executeRequest
import com.keygenqt.viewer.android.BuildConfig
import com.keygenqt.viewer.android.data.mappers.toModel
import com.keygenqt.viewer.android.data.requests.RefreshTokenRequest
import com.keygenqt.viewer.android.services.api.impl.ApiRefreshToken
import com.keygenqt.viewer.android.utils.AuthUser
import kotlin.reflect.full.createInstance

/**
 * Custom error handler
 */
fun errorHandlerStates(exception: Exception): ResponseState {
    return when (exception) {
        is ResponseException -> ResponseState.Error(exception)
        else -> ResponseState.Error(ResponseException.ExceptionUnknown())
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
 * [executeRequest] + refresh token
 */
suspend inline fun <T> executeRefreshToken(
    api: ApiRefreshToken,
    emit: Boolean = true,
    crossinline body: suspend () -> T
): ResponseResult<T> {
    return try {
        ResponseResult.Success(body.invoke())
    } catch (e: Exception) {
        // get refresh
        val refreshToken = AuthUser.data?.refreshToken
        // check
        if (e is ResponseException.TokenExpired && refreshToken != null) {
            // query refresh
            val response = executeRequest(emit = false) {
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
                executeRequest { body.invoke() }
            } else {
                if (emit) {
                    RequestHandler.emit(e)
                }
                ResponseResult.Error(e)
            }
        } else {
            if (emit) {
                RequestHandler.emit(e)
            }
            ResponseResult.Error(e)
        }
    }
}
