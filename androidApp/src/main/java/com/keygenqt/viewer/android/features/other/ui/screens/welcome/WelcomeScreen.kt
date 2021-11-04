/**
 * Copyright © 2021 Surf. All rights reserved.
 */
package com.keygenqt.viewer.android.features.other.ui.screens.welcome

import androidx.compose.runtime.Composable
import com.keygenqt.viewer.android.base.LocalViewModel
import com.keygenqt.viewer.android.features.followers.ui.actions.FollowersMainActions
import com.keygenqt.viewer.android.features.followers.ui.viewModels.FollowersViewModel
import com.keygenqt.viewer.android.features.other.ui.actions.WelcomeActions
import com.keygenqt.viewer.android.features.other.ui.viewModels.OtherViewModel

/**
 * Base page fun for initialization
 *
 * @param viewModel page view model
 * @param onActions actions for page
 */
@Composable
fun WelcomeScreen(
    viewModel: OtherViewModel,
    onActions: (WelcomeActions) -> Unit = {},
) {
    WelcomeBody(
        onActions = onActions,
        appViewModel = LocalViewModel.current
    )
}
