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
package com.keygenqt.viewer.android.features.followers.ui.screens.followers

import android.content.res.Configuration
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.keygenqt.viewer.android.R
import com.keygenqt.routing.NavigationDispatcher
import com.keygenqt.viewer.android.compose.base.AppScaffold
import com.keygenqt.viewer.android.compose.overload.AppSwipeRefreshList
import com.keygenqt.viewer.android.data.mock.mock
import com.keygenqt.viewer.android.data.models.FollowerModel
import com.keygenqt.viewer.android.features.followers.ui.actions.FollowersActions
import com.keygenqt.viewer.android.theme.AppTheme
import kotlinx.coroutines.flow.flowOf

@Composable
fun FollowersBody(
    models: LazyPagingItems<FollowerModel>,
    uriHandler: UriHandler? = null,
    navDispatcher: NavigationDispatcher? = null,
    onActions: (FollowersActions) -> Unit = {},
) {
    val listState = rememberLazyListState()
    val refreshState = rememberSwipeRefreshState(models.loadState.refresh is LoadState.Loading)

    AppScaffold(
        navigationDispatcher = navDispatcher,
        topBarTitle = stringResource(id = R.string.followers_title)
    ) {
        AppSwipeRefreshList(
            listState = listState,
            refreshState = refreshState,
            items = models,
        ) { _, item ->
            FollowersItem(
                model = item,
                uriHandler = uriHandler
            )
        }
    }
}

// @todo preview items not working
// https://issuetracker.google.com/issues/194544557
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PIXEL_4)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.PIXEL_4)
@Composable
private fun Preview() {
    AppTheme {
        FollowersBody(
            models = flowOf(
                PagingData.from(
                    listOf(FollowerModel.mock(), FollowerModel.mock(), FollowerModel.mock())
                )
            ).collectAsLazyPagingItems()
        )
    }
}
