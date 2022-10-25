package com.keygenqt.viewer.extensions

import kotlinx.datetime.Instant

fun String?.toTimestamp(): Long =
    this?.let { Instant.parse(it).toEpochMilliseconds() } ?: 0