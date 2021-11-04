package com.keygenqt.viewer.android.base

import androidx.navigation.NavHostController
import com.keygenqt.viewer.android.features.followers.navigation.actions.FollowersActions
import com.keygenqt.viewer.android.features.other.navigation.actions.OtherActions
import com.keygenqt.viewer.android.features.repos.navigation.actions.ReposActions
import com.keygenqt.viewer.android.features.stats.navigation.actions.StatsActions

class AppActions(override val controller: NavHostController) :
    FollowersActions,
    ReposActions,
    StatsActions,
    OtherActions
