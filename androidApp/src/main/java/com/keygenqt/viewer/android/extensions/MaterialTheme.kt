package com.keygenqt.viewer.android.extensions

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.keygenqt.viewer.android.extensions.DarkenColorLabel.getDarkenColor

/**
 * Get darken color label for light theme
 */
object DarkenColorLabel {
    private var value: Color? = null
    fun getDarkenColor(color: Color) = value ?: run { value = color.darkenColor(0.73f); value!! }
}

/**
 * Custom colors for material 3 theme for material 2
 */
@Composable
fun MaterialTheme.textFieldColors(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
): TextFieldColors {
    val labelColor = if (useDarkTheme) colorScheme.primaryContainer else getDarkenColor(colorScheme.primary)
    return TextFieldDefaults.textFieldColors(
        backgroundColor = colorScheme.surfaceVariant,
        textColor = colorScheme.onSurfaceVariant,
        cursorColor = colorScheme.onSurfaceVariant,
        focusedLabelColor = labelColor,
        unfocusedLabelColor = labelColor,
        focusedIndicatorColor = labelColor,
    )
}