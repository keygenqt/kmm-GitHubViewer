/*
 * Copyright 2021 Vitaliy Zarubin
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
package com.keygenqt.viewer.android.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

/**
 * Collapse list animation
 */
@Composable
fun LazyColumnCollapse(
    modifier: Modifier = Modifier,
    lazyListState: LazyListState = rememberLazyListState(),
    contentTop: @Composable ColumnScope.() -> Unit,
    content: LazyListScope.() -> Unit
) {
    var scrolledY by remember { mutableStateOf(0f) }
    var previousOffset by remember { mutableStateOf(0) }

    LazyColumn(
        modifier = modifier,
        state = lazyListState
    ) {
        item {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .graphicsLayer {
                        scrolledY += lazyListState.firstVisibleItemScrollOffset - previousOffset
                        translationY = scrolledY * 0.5f
                        previousOffset = lazyListState.firstVisibleItemScrollOffset
                    }
            ) {
                contentTop.invoke(this)
            }
        }
        content.invoke(this)
    }
}
