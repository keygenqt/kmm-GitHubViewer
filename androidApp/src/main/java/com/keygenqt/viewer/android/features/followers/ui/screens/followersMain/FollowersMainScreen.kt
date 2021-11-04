/**
 * Copyright © 2021 Surf. All rights reserved.
 */
package com.keygenqt.viewer.android.features.followers.ui.screens.followersMain

import androidx.compose.runtime.Composable
import com.keygenqt.viewer.android.base.LocalViewModel
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
    FollowersMainBody(
        onActions = onActions,
        appViewModel = LocalViewModel.current
    )
}
