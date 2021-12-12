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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.keygenqt.accompanist.SwipeRefreshList
import com.keygenqt.viewer.android.compose.components.lottie.ListEmptyAnimation
import com.keygenqt.viewer.android.compose.components.lottie.ListErrorAnimation
import com.keygenqt.viewer.android.compose.components.lottie.ListLoadingAnimation
import com.keygenqt.viewer.android.compose.components.lottie.LoadingBlockAnimation

/**
 * Overload for app [SwipeRefreshList]
 */
@Composable
fun <T : Any> AppSwipeRefreshList(
    items: LazyPagingItems<T>,
    listState: LazyListState,
    refreshState: SwipeRefreshState,
    content: @Composable (Int, T) -> Unit,
) {
    SwipeRefreshList(
        refreshState = refreshState,
        listState = listState,
        items = items,
        indicator = { s, trigger ->
            SwipeRefreshIndicator(
                state = s,
                refreshTriggerDistance = trigger,
                backgroundColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            )
        },
        contentLoadState = {
            Column {
                LoadingBlockAnimation(Modifier.fillMaxSize())
                Spacer(modifier = Modifier.size(16.dp))
            }
        },
        contentLoading = {
            ListLoadingAnimation()
        },
        contentEmpty = {
            ListEmptyAnimation()
        },
        contentError = {
            ListErrorAnimation()
        },
        content = content,
    )
}
