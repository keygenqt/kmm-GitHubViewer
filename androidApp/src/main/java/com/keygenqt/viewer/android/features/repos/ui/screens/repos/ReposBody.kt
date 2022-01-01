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
package com.keygenqt.viewer.android.features.repos.ui.screens.repos

import android.content.res.Configuration
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.keygenqt.viewer.android.R
import com.keygenqt.viewer.android.compose.base.AppScaffold
import com.keygenqt.viewer.android.compose.base.RotateIconSortIcon
import com.keygenqt.viewer.android.compose.overload.AppSwipeRefreshList
import com.keygenqt.viewer.android.data.models.RepoModel
import com.keygenqt.viewer.android.features.repos.ui.actions.ReposActions
import com.keygenqt.viewer.android.theme.AppTheme
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

/**
 * Body for [ReposScreen]
 */
@Composable
fun ReposBody(
    isSortDescListRepo: Boolean = false,
    models: LazyPagingItems<RepoModel>,
    onActions: (ReposActions) -> Unit = {},
) {
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val refreshState = rememberSwipeRefreshState(models.loadState.refresh is LoadState.Loading)

    AppScaffold(
        topBarTitle = stringResource(id = R.string.repos_title),
        topBarActions = {
            RotateIconSortIcon(
                enabled = models.loadState.refresh !is LoadState.Loading,
                isRotate = isSortDescListRepo
            ) {
                scope.launch {
                    listState.animateScrollToItem(index = 0)
                    onActions(ReposActions.SortToggle)
                    models.refresh()
                }
            }
        }
    ) {
        AppSwipeRefreshList(
            listState = listState,
            refreshState = refreshState,
            items = models,
        ) { _, item ->
            ReposItem(
                model = item,
                onActions = onActions
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PIXEL_4)
@Composable
private fun Preview() {
    AppTheme {
        AppTheme {
            ReposBody(
                models = flowOf(
                    PagingData.from(
                        listOf<RepoModel>()
                    )
                ).collectAsLazyPagingItems()
            )
        }
    }
}
