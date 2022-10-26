package com.keygenqt.viewer.extensions

import kotlinx.datetime.Instant

/**
 * Get timestamp for string
 */
fun String?.toTimestamp(): Long =
    this?.let { Instant.parse(it).toEpochMilliseconds() } ?: 0