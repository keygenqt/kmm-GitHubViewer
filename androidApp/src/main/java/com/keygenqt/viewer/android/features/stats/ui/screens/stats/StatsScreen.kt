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
package com.keygenqt.viewer.android.features.stats.ui.screens.stats

import androidx.compose.runtime.Composable
import com.keygenqt.viewer.android.base.LocalNavigationDispatcher
import com.keygenqt.viewer.android.base.LocalViewModel
import com.keygenqt.viewer.android.features.stats.ui.actions.StatsActions
import com.keygenqt.viewer.android.features.stats.ui.viewModels.StatsViewModel

/**
 * Base page fun for initialization
 *
 * @param viewModel page view model
 * @param onActions actions for page
 */
@Composable
fun StatsScreen(
    viewModel: StatsViewModel,
    onActions: (StatsActions) -> Unit = {},
) {
    StatsBody(
        onActions = onActions,
        navDispatcher = LocalNavigationDispatcher.current
    )
}
