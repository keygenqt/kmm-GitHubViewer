/**
 * Copyright © 2021 Surf. All rights reserved.
 */
package com.keygenqt.viewer.android.features.other.navigation.graph

import androidx.navigation.NavGraphBuilder
import com.keygenqt.viewer.android.base.AppActions
import com.keygenqt.viewer.android.features.other.navigation.graph.impl.welcomeGraph

/**
 * Base block with graphs for feature
 */
fun NavGraphBuilder.otherNavGraph(
    appActions: AppActions,
) {
    welcomeGraph(appActions)
}
