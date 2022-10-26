package com.keygenqt.viewer.extensions

import kotlinx.datetime.Instant
import kotlinx.datetime.toNSDate
import platform.Foundation.*

/**
 * Format timestamp
 */
actual fun Long.dateFormat(format: String): String =
    Instant.fromEpochMilliseconds(this).toNSDate().let {
        NSDateFormatter().apply {
            timeZone = NSTimeZone.localTimeZone
            locale = NSLocale.autoupdatingCurrentLocale
            dateFormat = format
        }.stringFromDate(it)
    }
