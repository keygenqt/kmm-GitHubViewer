package com.keygenqt.viewer.extensions

/**
 * Cross platform round double
 */
fun Double?.round(size: Int = 2): Double =
    this?.let { (it.toInt().toString() + "." + it.toString().substringAfter(".").take(size)).toDouble() }
        ?: 0.toDouble()