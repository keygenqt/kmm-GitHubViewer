/**
 * Copyright © 2021 Surf. All rights reserved.
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
