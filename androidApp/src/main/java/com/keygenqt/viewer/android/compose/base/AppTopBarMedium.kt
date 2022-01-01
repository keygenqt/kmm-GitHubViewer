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
package com.keygenqt.viewer.android.compose.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.keygenqt.viewer.android.base.LocalNavigationDispatcher
import com.keygenqt.viewer.android.base.NavigationDispatcher
import com.keygenqt.viewer.android.compose.lottie.LoadingBlockAnimation

/**
 * App top bar medium material 3
 */
@Composable
fun AppTopBarMedium(
    title: String,
    scrollBehavior: TopAppBarScrollBehavior,
    loading: Boolean = false,
    actions: @Composable ((RowScope) -> Unit)? = null,
    navDispatcher: NavigationDispatcher = LocalNavigationDispatcher.current,
) {
    MediumTopAppBar(
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ),
        scrollBehavior = scrollBehavior,
        navigationIcon = if (navDispatcher.hasEnabledCallbacks()) {
            {
                IconButton(onClick = {
                    navDispatcher.onBackPressed()
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        } else {
            {}
        },
        title = {
            Column {
                Spacer(modifier = Modifier.size(8.dp))
                Text(text = title)
            }
        },
        actions = {
            if (loading) {
                // show loading indicator
                LoadingBlockAnimation()
            } else {
                // custom actions
                actions?.invoke(this)
            }
        }
    )
}
