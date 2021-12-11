package com.keygenqt.viewer.android.compose.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.keygenqt.accompanist.SwipeRefreshList

/**
 * Overload for app [SwipeRefreshList]
 */
@Composable
fun <T : Any> AppSwipeRefreshList(
    items: LazyPagingItems<T>,
    refreshState: SwipeRefreshState,
    content: @Composable (Int, T) -> Unit,
) {
    SwipeRefreshList(
        items = items,
        indicator = { s, trigger ->
            SwipeRefreshIndicator(
                state = s,
                refreshTriggerDistance = trigger,
                backgroundColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            )
        },
        state = refreshState,
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
        content = content,
    )
}