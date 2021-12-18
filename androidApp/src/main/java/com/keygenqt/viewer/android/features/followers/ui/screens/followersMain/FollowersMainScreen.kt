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
package com.keygenqt.viewer.android.features.followers.ui.screens.followersMain

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalUriHandler
import androidx.paging.compose.collectAsLazyPagingItems
import com.keygenqt.viewer.android.features.followers.ui.actions.FollowersMainActions
import com.keygenqt.viewer.android.features.followers.ui.viewModels.FollowersViewModel

/**
 * Base page fun for initialization
 *
 * @param viewModel page view model
 * @param onActions actions for page
 */
@Composable
fun FollowersMainScreen(
    viewModel: FollowersViewModel,
    onActions: (FollowersMainActions) -> Unit = {},
) {

    val listFollowers = viewModel.listFollowers.collectAsLazyPagingItems()

    FollowersMainBody(
        models = listFollowers,
        uriHandler = LocalUriHandler.current,
        onActions = onActions
    )
}
