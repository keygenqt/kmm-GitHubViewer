package com.keygenqt.viewer.android.features.repos.navigation.actions.impl

import androidx.navigation.NavHostController
import com.keygenqt.viewer.android.features.repos.navigation.nav.ReposNav
import com.keygenqt.viewer.android.features.repos.ui.screens.followersMain.ReposMainScreen

/**
 * Actions for [ReposMainScreen]
 */
interface ReposMainActions {

    val controller: NavHostController

    /**
     * To main page followers
     */
    fun toReposMain() {
        controller.navigate(ReposNav.navReposMain.reposMainScreen.route)
    }
}
