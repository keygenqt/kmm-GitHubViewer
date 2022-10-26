package com.keygenqt.viewer.extensions

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Format timestamp
 */
actual fun Long.dateFormat(format: String): String =
    Instant.fromEpochMilliseconds(this)
        .toLocalDateTime(TimeZone.UTC)
        .toJavaLocalDateTime()
        .format(DateTimeFormatter.ofPattern(format))