/**
 * Copyright © 2021 Surf. All rights reserved.
 */
package com.keygenqt.viewer.android.features.stats.ui.screens.statsMain

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
import com.keygenqt.viewer.android.features.stats.ui.actions.StatsMainActions
import com.keygenqt.viewer.android.theme.AppTheme

@Composable
fun StatsMainBody(
    appViewModel: AppViewModel? = null,
    onActions: (StatsMainActions) -> Unit = {},
) {
    val scrollState = rememberScrollState()

    // disable scroll if page not need scroll
    appViewModel?.setScrollState(scrollState.value > 0)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.tertiaryContainer)
            .navigationBarsPaddingMaterial3()
            .verticalScroll(scrollState)
    ) {
        Column(Modifier.padding(20.dp)) {
            Text(
                modifier = Modifier,
                text = Greeting().greeting()
            )

            Spacer(modifier = Modifier.size(20.dp))

            Spacer(
                modifier = Modifier
                    .height(0.5.dp)
                    .fillMaxWidth()
                    .background(Color.Gray)
            )

            Spacer(modifier = Modifier.size(20.dp))

            Text(
                modifier = Modifier,
                text = "Kotlin multiplatform mobile"
            )

            Spacer(modifier = Modifier.size(1000.dp))

            Text(
                modifier = Modifier,
                text = "Scroll bottom"
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PIXEL_4)
@Composable
private fun Preview() {
    AppTheme {
        StatsMainBody()
    }
}
