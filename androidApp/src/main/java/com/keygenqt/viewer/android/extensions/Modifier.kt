package com.keygenqt.viewer.android.extensions

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsPadding

fun Modifier.navigationBarsPaddingMaterial3() = this
    .navigationBarsPadding()
    .padding(bottom = 30.dp)