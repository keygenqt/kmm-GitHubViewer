/**
 * Copyright © 2021 Surf. All rights reserved.
 */
package com.keygenqt.viewer.android.extensions

import com.keygenqt.viewer.android.base.exceptions.DataException
import com.keygenqt.viewer.android.base.exceptions.RESPONSE_JSON_ERROR
import com.keygenqt.viewer.android.base.exceptions.ResponseException
import com.keygenqt.viewer.android.utils.AppHelper.isValidJson
import com.keygenqt.viewer.android.utils.ConstantsApp
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONObject
import retrofit2.Response
import timber.log.Timber
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
                throw Json.decodeFromString<DataException>(string)
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
    var response = this
    body?.let { body ->
        val data = body.string()

        // check is not valid json
        if (!isValidJson(data)) {
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
            response = if (obj.has("error")) {
                obj.put("code", RESPONSE_JSON_ERROR)
                response.newBuilder()
                    .code(RESPONSE_JSON_ERROR)
                    .body(obj.toString().toResponseBody("application/json".toMediaType()))
                    .build()
            } else {
                response.newBuilder()
                    .body(obj.toString().toResponseBody("application/json".toMediaType()))
                    .build()
            }
        }
    }
    return response
}