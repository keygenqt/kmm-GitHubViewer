/**
 * Copyright © 2021 Surf. All rights reserved.
 */
package com.keygenqt.viewer.android.features.stats.ui.screens.statsMain

import androidx.compose.runtime.Composable
import com.keygenqt.viewer.android.base.LocalViewModel
import com.keygenqt.viewer.android.features.stats.ui.actions.StatsMainActions
import com.keygenqt.viewer.android.features.stats.ui.viewModels.StatsViewModel

/**
 * Base page fun for initialization
 *
 * @param viewModel page view model
 * @param onActions actions for page
 */
@Composable
fun StatsMainScreen(
    viewModel: StatsViewModel,
    onActions: (StatsMainActions) -> Unit = {},
) {
    StatsMainBody(
        onActions = onActions,
        appViewModel = LocalViewModel.current
    )
}
