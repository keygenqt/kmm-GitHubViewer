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
package com.keygenqt.viewer.android.base

import com.keygenqt.viewer.android.R
import com.keygenqt.viewer.android.features.followers.navigation.nav.FollowersNav
import com.keygenqt.viewer.android.features.other.navigation.nav.OtherNav
import com.keygenqt.viewer.android.features.profile.navigation.nav.ProfileNav
import com.keygenqt.viewer.android.features.stats.navigation.nav.StatsNav

/**
 * Titles pages
 */
enum class TopBarTitle(
    val route: String,
    val titleTopBar: Int?,
) {
    SIGN_IN(
        OtherNav.navSignIn.signInScreen.route,
        R.string.sign_in_title,
    ),
    FOLLOWERS(
        FollowersNav.navFollowersMain.followersMainScreen.route,
        R.string.followers_title,
    ),
    STATS(
        StatsNav.navStatsMain.statsMainScreen.route,
        R.string.stats_title,
    ),
    PROFILE(
        ProfileNav.navProfileMain.profileMainScreen.route,
        R.string.profile_title,
    );

    companion object {
        fun String.findTitleByRoute(): TopBarTitle? {
            for (tab in values()) {
                if (tab.route == this) {
                    return tab
                }
            }
            return null
        }
    }
}
