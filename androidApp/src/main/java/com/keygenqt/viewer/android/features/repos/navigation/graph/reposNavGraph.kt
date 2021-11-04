package com.keygenqt.viewer.android.features.repos.navigation.graph

import androidx.navigation.NavGraphBuilder
import com.keygenqt.viewer.android.base.AppActions
import com.keygenqt.viewer.android.features.repos.navigation.graph.impl.reposMainGraph

/**
 * Base block with graphs for module
 */
fun NavGraphBuilder.reposNavGraph(
    appActions: AppActions,
) {
    reposMainGraph(appActions)
}
