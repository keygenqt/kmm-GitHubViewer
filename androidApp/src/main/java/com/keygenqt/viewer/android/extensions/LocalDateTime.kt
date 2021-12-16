package com.keygenqt.viewer.android.extensions

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import java.time.format.DateTimeFormatter


/**
 * Format [LocalDateTime]
 */
fun LocalDateTime?.formatDate() =
    this?.toJavaLocalDateTime()?.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) ?: ""