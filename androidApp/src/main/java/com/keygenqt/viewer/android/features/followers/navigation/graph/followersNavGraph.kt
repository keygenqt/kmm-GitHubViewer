/**
 * Copyright © 2021 Surf. All rights reserved.
 */
package com.keygenqt.viewer.android.features.followers.navigation.graph

import androidx.navigation.NavGraphBuilder
import com.keygenqt.viewer.android.base.AppActions
import com.keygenqt.viewer.android.features.followers.navigation.graph.impl.followersMainGraph

/**
 * Base block with graphs for module
 */
fun NavGraphBuilder.followersNavGraph(
    appActions: AppActions,
) {
    followersMainGraph(appActions)
}
