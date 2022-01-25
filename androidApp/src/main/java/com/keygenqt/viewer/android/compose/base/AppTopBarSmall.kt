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
package com.keygenqt.viewer.android.compose.base

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.keygenqt.routing.NavigationDispatcher
import com.keygenqt.viewer.android.compose.lottie.LoadingBlockAnimation
import com.keygenqt.viewer.android.compose.texts.TextTitleLarge
import com.keygenqt.viewer.android.extensions.AnimatedNavGraphState
import com.keygenqt.viewer.android.extensions.LaunchedEffectAnimation
import kotlinx.coroutines.launch

/**
 * App top bar small material 3
 */
@Composable
fun <T> AppTopBarSmall(
    title: String,
    scrollBehavior: TopAppBarScrollBehavior,
    navigationDispatcher: NavigationDispatcher?,
    backData: T? = null,
    loading: Boolean = false,
    actions: @Composable ((RowScope) -> Unit)? = null,
) {
    val scope = rememberCoroutineScope()

    // remember has callbacks
    var hasEnabledCallbacks: Boolean by remember {
        mutableStateOf(navigationDispatcher?.hasEnabledCallbacks() == true)
    }

    // update hasEnabledCallbacks if animation end
    LaunchedEffectAnimation { state ->
        if (state == AnimatedNavGraphState.END) {
            hasEnabledCallbacks = navigationDispatcher?.hasEnabledCallbacks() == true
        }
    }

    // update hasEnabledCallbacks if pager change
    LaunchedEffect(Unit) {
        navigationDispatcher?.listenChangePager(scope) {
            hasEnabledCallbacks = navigationDispatcher.hasEnabledCallbacks() == true
        }
    }

    SmallTopAppBar(
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ),
        scrollBehavior = scrollBehavior,
        navigationIcon = if (hasEnabledCallbacks) {
            {
                IconButton(onClick = {
                    scope.launch {
                        backData?.let {
                            navigationDispatcher?.onBackPressed(it)
                        } ?: run {
                            navigationDispatcher?.onBackPressed()
                        }
                    }
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
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.size(8.dp))
                TextTitleLarge(text = title, maxLines = 1)
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
