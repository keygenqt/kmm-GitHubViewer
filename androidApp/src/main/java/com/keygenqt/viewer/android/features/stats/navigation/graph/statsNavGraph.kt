/**
 * Copyright © 2021 Surf. All rights reserved.
 */
package com.keygenqt.viewer.android.features.stats.navigation.graph

import androidx.navigation.NavGraphBuilder
import com.keygenqt.viewer.android.base.AppActions
import com.keygenqt.viewer.android.features.stats.navigation.graph.impl.statsMainGraph

/**
 * Base block with graphs for module
 */
fun NavGraphBuilder.statsNavGraph(
    appActions: AppActions,
) {
    statsMainGraph(appActions)
}
