package com.keygenqt.viewer.android.compose.components

import androidx.activity.OnBackPressedDispatcher
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
import com.keygenqt.viewer.android.base.LocalBackPressedDispatcher
import com.keygenqt.viewer.android.compose.components.lottie.LoadingBlockAnimation

@Composable
fun AppSmallTopAppBar(
    title: String,
    scrollBehavior: TopAppBarScrollBehavior,
    loading: Boolean = false,
    actions: @Composable ((RowScope) -> Unit)? = null,
    backDispatcher: OnBackPressedDispatcher = LocalBackPressedDispatcher.current,
) {
    SmallTopAppBar(
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ),
        scrollBehavior = scrollBehavior,
        navigationIcon = if (backDispatcher.hasEnabledCallbacks()) {
            {
                IconButton(onClick = {
                    backDispatcher.onBackPressed()
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