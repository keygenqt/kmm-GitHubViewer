/**
 * Copyright © 2021 Surf. All rights reserved.
 */
package com.keygenqt.viewer.android.features.repos.ui.screens.followersMain

import androidx.compose.runtime.Composable
import com.keygenqt.viewer.android.base.LocalViewModel
import com.keygenqt.viewer.android.features.repos.ui.actions.ReposMainActions
import com.keygenqt.viewer.android.features.repos.ui.viewModels.ReposViewModel

/**
 * Base page fun for initialization
 *
 * @param viewModel page view model
 * @param onActions actions for page
 */
@Composable
fun ReposMainScreen(
    viewModel: ReposViewModel,
    onActions: (ReposMainActions) -> Unit = {},
) {
    ReposMainBody(
        onActions = onActions,
        appViewModel = LocalViewModel.current
    )
}
