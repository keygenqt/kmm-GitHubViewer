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

/**
 * Response exception
 *
 * @author Vitaliy Zarubin
 */
sealed class ResponseException(val code: Int, error: String) : RuntimeException(error) {

    /**
     * Exception 404
     */
    class NotFound(
        error: String = "Not found"
    ) : ResponseException(RESPONSE_NOT_FOUND, error)

    /**
     * Exception unhandled
     */
    class ExceptionUnknown(
        code: Int = -1,
        error: String = "Unknown error"
    ) : ResponseException(code, error)
}
