/*
 * Copyright 2022 Vitaliy Zarubin
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
package com.keygenqt.viewer.android.menu

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.keygenqt.viewer.android.R
import com.keygenqt.viewer.android.features.followers.navigation.route.FollowersNavRoute
import com.keygenqt.viewer.android.features.profile.navigation.route.ProfileNavRoute
import com.keygenqt.viewer.android.features.repos.navigation.route.ReposNavRoute

enum class MenuTab(
    val route: String,
    val titleTopBar: Int?,
    val icon: ImageVector,
) {
    REPOS(
        ReposNavRoute.repos.default.route,
        null,
        Icons.Filled.List
    ),
    FOLLOWERS(
        FollowersNavRoute.followers.default.route,
        R.string.followers_title,
        Icons.Filled.People
    ),
    PROFILE(
        ProfileNavRoute.profile.default.route,
        R.string.profile_title,
        Icons.Filled.Person
    );
//    @todo
//    STATS(
//        StatsNav.navStatsMain.statsMainScreen.route,
//        R.string.stats_title,
//        Icons.Filled.BarChart
//    );
}
