package com.keygenqt.viewer.android.features.stats.navigation.actions.impl

import androidx.navigation.NavHostController
import com.keygenqt.viewer.android.features.stats.navigation.nav.StatsNav
import com.keygenqt.viewer.android.features.stats.ui.screens.statsMain.StatsMainScreen

/**
 * Actions for [StatsMainScreen]
 */
interface StatsMainActions {

    val controller: NavHostController

    /**
     * To main page followers
     */
    fun toStatsMain() {
        controller.navigate(StatsNav.navStatsMain.statsMainScreen.route)
    }
}
