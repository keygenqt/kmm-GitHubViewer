package com.keygenqt.viewer.android.compose.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate

@Composable
fun RotateIconSort(
    isRotate: Boolean = true,
    onClick: () -> Unit = {}
) {
    var isEnabled by remember { mutableStateOf(true) }

    val angle: Float by animateFloatAsState(
        targetValue = if (isRotate) 0F else 180F,
        animationSpec = tween(
            durationMillis = 600, // duration
            easing = FastOutSlowInEasing
        ),
        finishedListener = {
            // disable the button
            isEnabled = true
        }
    )

    IconButton(
        enabled = isEnabled,
        onClick = {
            isEnabled = false
            onClick.invoke()
        }
    ) {
        Icon(
            modifier = Modifier.rotate(angle),
            imageVector = Icons.Default.Sort,
            contentDescription = "Sort",
            tint = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}