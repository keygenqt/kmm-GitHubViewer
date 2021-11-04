package com.keygenqt.viewer.android.features.followers.navigation.actions.impl

import androidx.navigation.NavHostController
import com.keygenqt.viewer.android.features.followers.navigation.nav.FollowersNav
import com.keygenqt.viewer.android.features.followers.ui.screens.followersMain.FollowersMainScreen

/**
 * Actions for [FollowersMainScreen]
 */
interface FollowersMainActions {

    val controller: NavHostController

    /**
     * To main page followers
     */
    fun toFollowersMain() {
        controller.navigate(FollowersNav.navFollowersMain.followersMainScreen.route)
    }
}
