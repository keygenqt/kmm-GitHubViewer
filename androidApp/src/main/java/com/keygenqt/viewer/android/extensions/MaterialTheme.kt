/*
 * Copyright 2022 Vitaliy Zarubin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
