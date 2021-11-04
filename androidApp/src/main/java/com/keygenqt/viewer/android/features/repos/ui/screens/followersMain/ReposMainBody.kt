package com.keygenqt.viewer.android.features.repos.ui.screens.followersMain

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.insets.navigationBarsPadding
import com.keygenqt.viewer.android.base.AppViewModel
import com.keygenqt.viewer.android.extensions.navigationBarsPaddingMaterial3
import com.keygenqt.viewer.android.features.repos.ui.actions.ReposMainActions
import com.keygenqt.viewer.android.theme.AppTheme

@Composable
fun ReposMainBody(
    appViewModel: AppViewModel? = null,
    onActions: (ReposMainActions) -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .navigationBarsPaddingMaterial3()
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Repos"
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PIXEL_4)
@Composable
private fun Preview() {
    AppTheme {
        ReposMainBody()
    }
}
