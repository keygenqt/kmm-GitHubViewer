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

import androidx.navigation.NavHostController
import com.keygenqt.viewer.android.features.followers.navigation.actions.FollowersNavActions
import com.keygenqt.viewer.android.features.other.navigation.actions.OtherNavActions
import com.keygenqt.viewer.android.features.profile.navigation.actions.ProfileNavActions
import com.keygenqt.viewer.android.features.repos.navigation.actions.ReposNavActions
import com.keygenqt.viewer.android.features.stats.navigation.actions.StatsNavActions

/**
 * Base actions with all feature
 */
class AppActions(override val controller: NavHostController) :
    ProfileNavActions,
    FollowersNavActions,
    ReposNavActions,
    StatsNavActions,
    OtherNavActions
