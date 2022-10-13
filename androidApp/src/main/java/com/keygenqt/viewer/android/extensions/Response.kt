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
package com.keygenqt.viewer.android.extensions

import com.keygenqt.viewer.android.base.exceptions.DataException
import com.keygenqt.viewer.android.base.exceptions.ResponseException
import com.keygenqt.viewer.android.base.exceptions.toResponseException
import com.keygenqt.viewer.android.utils.AppHelper.isValidJson
import com.keygenqt.viewer.android.utils.ConstantsApp
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONObject
import retrofit2.Response

/**
 * Simulate slow internet
 */
fun <T> Response<T>.delay(enable: Boolean): Response<T> {
    if (enable) {
        runBlocking {
            delay(ConstantsApp.DEBUG_DELAY)
        }
    }
    return this
}

/**
 * Check status HTTP response retrofit2
 */
fun <T> Response<T>.responseCheck(): Response<T> {
    if (isSuccessful) {
        return this
    } else {
        errorBody()?.let {
            val string = it.string()
            if (string.isNotBlank()) {
                val error = Json.decodeFromString<DataException>(string)
                if (error.code != 0) {
                    throw error
                }
            }
        }
        throw code().toResponseException()
    }
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
            obj.put("code", code)
            newBuilder()
                .code(code)
                .body(obj.toString().toResponseBody("application/json".toMediaType()))
                .build()
        }
    } else {
        if (code == 200) {
            try {
                // check error with 200
                val obj = JSONObject(data)
                if (obj.has("error") || obj.has("message") && obj.has("documentation_url")) {
                    val code = ResponseException.SuccessError().code
                    obj.put("code", code)
                    newBuilder()
                        .code(code)
                        .body(obj.toString().toResponseBody("application/json".toMediaType()))
                        .build()
                } else {
                    this
                }
            } catch (e: Exception) {
                this
            }
        } else {
            this
        }
    }
}
