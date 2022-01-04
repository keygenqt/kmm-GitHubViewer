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
package com.keygenqt.viewer.android.base.exceptions

import com.keygenqt.viewer.android.R

/**
 * Response exception
 *
 * @author Vitaliy Zarubin
 */
sealed class ResponseException(val code: Int, val resId: Int) : RuntimeException() {

    /**
     * Token expired exception
     */
    class TokenExpired(
        code: Int = 401,
        resId: Int = R.string.error_401
    ) : ResponseException(code, resId)

    /**
     * Not found exception
     */
    class NotFound(
        code: Int = 404,
        resId: Int = R.string.error_404
    ) : ResponseException(code, resId)

    /**
     * Exception parse response
     */
    class JsonParse(
        code: Int = 1,
        resId: Int = R.string.error_json_parse
    ) : ResponseException(code, resId)

    /**
     * Exception with response 200
     */
    class SuccessError(
        code: Int = 2,
        resId: Int = R.string.error_success_error
    ) : ResponseException(code, resId)

    /**
     * Exception unhandled
     */
    class ExceptionUnknown(
        code: Int = -1,
        resId: Int = R.string.error_exception_unknown
    ) : ResponseException(code, resId)
}
