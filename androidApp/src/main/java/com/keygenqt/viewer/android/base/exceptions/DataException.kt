package com.keygenqt.viewer.android.base.exceptions

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

/**
 * Error server response
 */
@Immutable
@Serializable
data class DataException(
    val code: Int = 0,
    val error: String = "",
    val error_description: String = "",
    val error_uri: String = "",
    override val message: String = "",
    val documentation_url: String = "",
): RuntimeException()