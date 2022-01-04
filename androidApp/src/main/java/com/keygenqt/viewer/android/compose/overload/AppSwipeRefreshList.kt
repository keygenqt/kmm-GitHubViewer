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
package com.keygenqt.viewer.android.compose.overload

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.keygenqt.accompanist.SwipeRefreshList
import com.keygenqt.viewer.android.R
import com.keygenqt.viewer.android.compose.lottie.ListEmptyAnimation
import com.keygenqt.viewer.android.compose.lottie.ListErrorAnimation
import com.keygenqt.viewer.android.compose.lottie.LoadingBlockAnimation
import com.keygenqt.viewer.android.compose.texts.TextTitleMedium

/**
 * Overload for app [SwipeRefreshList]
 */
@Composable
fun <T : Any> AppSwipeRefreshList(
    items: LazyPagingItems<T>,
    listState: LazyListState,
    refreshState: SwipeRefreshState,
    content: @Composable (Int, T) -> Unit,
) = SwipeRefreshList(
    refreshState = refreshState,
    listState = listState,
    items = items,
    indicator = { st, tr ->
        AppSwipeRefreshIndicator(st, tr)
    },
    contentLoadState = {
        Column {
            LoadingBlockAnimation(Modifier.fillMaxSize())
            Spacer(modifier = Modifier.size(16.dp))
        }
    },
    contentLoading = {
        Box(modifier = Modifier.fillMaxSize()) {
            TextTitleMedium(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(id = R.string.app_loading)
            )
        }
    },
    contentEmpty = {
        ListEmptyAnimation()
    },
    contentError = {
        ListErrorAnimation()
    },
    content = content,
)
