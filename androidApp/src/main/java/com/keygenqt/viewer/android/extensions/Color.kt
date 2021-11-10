/**
 * Copyright © 2021 Surf. All rights reserved.
 */
package com.keygenqt.viewer.android.extensions

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

fun Color.darkenColor(value: Float = 0.3f): Color {
    return Color(
        android.graphics.Color.HSVToColor(
            FloatArray(3).apply {
                android.graphics.Color.colorToHSV(this@darkenColor.toArgb(), this)
                this[2] *= value
            }
        )
    )
}
