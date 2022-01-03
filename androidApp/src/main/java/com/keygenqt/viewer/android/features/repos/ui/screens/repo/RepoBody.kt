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
package com.keygenqt.viewer.android.features.repos.ui.screens.repo

import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.keygenqt.viewer.android.base.NavigationDispatcher
import com.keygenqt.viewer.android.base.queryActions.QueryState
import com.keygenqt.viewer.android.compose.base.AppScaffold
import com.keygenqt.viewer.android.data.mock.mock
import com.keygenqt.viewer.android.data.models.RepoModel
import com.keygenqt.viewer.android.features.repos.ui.actions.RepoActions
import com.keygenqt.viewer.android.theme.AppTheme
import kotlinx.coroutines.launch

/**
 * Body for [RepoScreen]
 */
@Composable
fun RepoBody(
    model: Any?,
    state1: QueryState = QueryState.Start,
    navDispatcher: NavigationDispatcher? = null,
    onActions: (RepoActions) -> Unit = {},
) {
    val scope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()

    // state query 1
    var loadingRepo by remember { mutableStateOf(false) }

    RepoQueryState1(
        state = state1,
        loadingRepo = {
            loadingRepo = true
        },
        clear = {
            loadingRepo = false
        }
    )

    AppScaffold(
        navigationDispatcher = navDispatcher,
        topBarLoading = model == false,
        topBarTitle = (model as? RepoModel)?.name ?: "",
        topBarActions = {
            IconButton(onClick = {
                scope.launch {
                    lazyListState.animateScrollToItem(0)
                    (model as? RepoModel)?.let {
                        onActions(RepoActions.ToUpdateRepo(it.id, Uri.parse(it.url)))
                    }
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        },
        swipeRefreshEnable = true,
        swipeRefreshLoading = loadingRepo,
        swipeRefreshAction = {
            onActions(RepoActions.ActionUpdateRepo)
        },
    ) {
        when (model) {
            is RepoModel -> RepoInfo(
                model = model,
                lazyListState = lazyListState
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PIXEL_4)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.PIXEL_4)
@Composable
private fun Preview() {
    AppTheme {
        RepoBody(RepoModel.mock())
    }
}
