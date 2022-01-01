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
package com.keygenqt.viewer.android.features.profile.ui.screens.profile

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.keygenqt.viewer.android.R
import com.keygenqt.viewer.android.base.queryActions.QueryState
import com.keygenqt.viewer.android.compose.base.AppScaffold
import com.keygenqt.viewer.android.data.models.UserModel
import com.keygenqt.viewer.android.features.profile.ui.actions.ProfileActions
import com.keygenqt.viewer.android.theme.AppTheme
import kotlinx.coroutines.launch

@Composable
fun ProfileBody(
    model: Any?,
    uriHandler: UriHandler? = null,
    state1: QueryState = QueryState.Start,
    onActions: (ProfileActions) -> Unit = {},
) {
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    var showDialogLogout by remember { mutableStateOf(false) }

    // state query 1
    var loadingUser by remember { mutableStateOf(false) }

    ProfileQueryState1(
        state = state1,
        loadingUser = {
            loadingUser = true
        },
        clear = {
            loadingUser = false
        }
    )

    AppScaffold(
        topBarLoading = model == false,
        topBarTitle = stringResource(id = R.string.profile_title),
        topBarActions = {
            IconButton(onClick = {
                showDialogLogout = true
            }) {
                Icon(
                    imageVector = Icons.Default.Logout,
                    contentDescription = "Logout",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            IconButton(onClick = {
                scope.launch {
                    scrollState.animateScrollTo(0)
                    onActions(ProfileActions.ToSettings)
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
        swipeRefreshLoading = loadingUser,
        swipeRefreshAction = {
            onActions(ProfileActions.ActionUpdateUser)
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            (model as? UserModel)?.let { ProfileInfo(it, uriHandler) }
        }
    }

    if (showDialogLogout) {
        CloseDialog(
            onDismissRequest = {
                showDialogLogout = false
            },
            confirmButton = {
                showDialogLogout = false
                onActions(ProfileActions.ActionLogout)
            },
            dismissButton = {
                showDialogLogout = false
            }
        )
    }
}

@Composable
fun CloseDialog(
    onDismissRequest: () -> Unit = {},
    confirmButton: () -> Unit = {},
    dismissButton: () -> Unit = {}
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(text = stringResource(id = R.string.profile_dialog_logout_title))
        },
        text = {
            Text(text = stringResource(id = R.string.profile_dialog_logout_text))
        },
        confirmButton = {
            TextButton(
                onClick = confirmButton
            ) {
                Text(stringResource(id = R.string.profile_dialog_logout_confirm))
            }
        },
        dismissButton = {
            TextButton(
                onClick = dismissButton
            ) {
                Text(stringResource(id = R.string.profile_dialog_logout_dismiss))
            }
        }
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PIXEL_4)
@Composable
private fun Preview() {
    AppTheme {
        ProfileBody(null)
    }
}
