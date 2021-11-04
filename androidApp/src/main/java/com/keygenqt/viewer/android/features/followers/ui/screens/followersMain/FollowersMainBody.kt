/**
 * Copyright © 2021 Surf. All rights reserved.
 */
package com.keygenqt.viewer.android.features.followers.ui.screens.followersMain

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsPadding
import com.keygenqt.githubviewer.Greeting
import com.keygenqt.viewer.android.R
import com.keygenqt.viewer.android.base.AppViewModel
import com.keygenqt.viewer.android.extensions.navigationBarsPaddingMaterial3
import com.keygenqt.viewer.android.features.followers.ui.actions.FollowersMainActions
import com.keygenqt.viewer.android.theme.AppTheme

@Composable
fun FollowersMainBody(
    appViewModel: AppViewModel? = null,
    onActions: (FollowersMainActions) -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.errorContainer)
            .navigationBarsPaddingMaterial3()
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(id = R.string.followers_title)
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PIXEL_4)
@Composable
private fun Preview() {
    AppTheme {
        FollowersMainBody()
    }
}
