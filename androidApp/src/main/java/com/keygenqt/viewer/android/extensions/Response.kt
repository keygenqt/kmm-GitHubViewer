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
package com.keygenqt.viewer.android.extensions

import com.keygenqt.response.LocalTryExecuteWithResponse
import com.keygenqt.response.LocalTryExecuteWithResponse.executeWithResponse
import com.keygenqt.response.ResponseResult
import com.keygenqt.response.extensions.success
import com.keygenqt.viewer.android.BuildConfig
import com.keygenqt.viewer.android.base.exceptions.ErrorModel
import com.keygenqt.viewer.android.base.exceptions.ResponseException
import com.keygenqt.viewer.android.data.mappers.toModel
import com.keygenqt.viewer.android.data.requests.RefreshTokenRequest
import com.keygenqt.viewer.android.services.api.impl.ApiRefreshToken
import com.keygenqt.viewer.android.utils.AppHelper.isValidJson
import com.keygenqt.viewer.android.utils.AuthUser
import com.keygenqt.viewer.android.utils.ConstantsApp
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONObject
import retrofit2.Response
import kotlin.reflect.full.createInstance

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

/**
 * Check status HTTP response retrofit2
 *
 * @author Vitaliy Zarubin
 */
@OptIn(ExperimentalSerializationApi::class)
fun <T> Response<T>.responseCheckApp(): Response<T> {
    if (isSuccessful) {
        return this
    } else {
        errorBody()?.let {
            val string = it.string()
            if (string.isNotBlank()) {
                throw Json.decodeFromString<ErrorModel>(string).code.toResponseException()
            }
        }
        throw code().toResponseException()
    }
}

/**
 * Code to class
 *
 * @author Vitaliy Zarubin
 */
private fun Int.toResponseException(): ResponseException {
    return ResponseException::class.sealedSubclasses
        .map { it.objectInstance ?: it.createInstance() }
        .firstOrNull { it.code == this }
        ?: ResponseException.ExceptionUnknown(code = this)
}

/**
 * Validate json from response
 */
fun okhttp3.Response.checkValidJson(): okhttp3.Response {

    val data = peekBody(Long.MAX_VALUE).string()

    // check is not valid json
    return if (!isValidJson(data)) {
        val obj = JSONObject()

        // check if contains data
        data.split("&").forEachIndexed { index, s ->
            if (s.contains("=")) {
                val (first, second) = s.split("=")
                obj.put(first, second)
            } else {
                obj.put("data_$index", s)
            }
        }
        // if has error data or not
        return if (obj.has("error")) {
            val code = ResponseException.JsonParse().code
            obj.put("code", code)
            newBuilder()
                .code(code)
                .body(obj.toString().toResponseBody("application/json".toMediaType()))
                .build()
        } else {
            newBuilder()
                .body(obj.toString().toResponseBody("application/json".toMediaType()))
                .build()
        }
    } else {
        this
    }
}

/**
 * [executeWithResponse] with refresh token
 */
suspend inline fun <T> executeRefreshToken(
    api: ApiRefreshToken,
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
            executeWithResponse(emit = false) {
                api.refreshToken(
                    request = RefreshTokenRequest(
                        refresh_token = refreshToken,
                        client_secret = BuildConfig.GITHUB_CLIENT_SECRET,
                        client_id = BuildConfig.GITHUB_CLIENT_ID,
                    )
                ).body()!!.toModel()
            }.success {
                AuthUser.login(it)
            }
            // re query
            executeWithResponse {
                body.invoke()
            }
        } else {
            LocalTryExecuteWithResponse.tryEmit(e)
            ResponseResult.Error(e)
        }
    }
}
