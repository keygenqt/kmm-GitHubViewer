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
package com.keygenqt.viewer.android.features.profile.navigation.actions.impl

import androidx.navigation.NavHostController
import com.keygenqt.viewer.android.features.profile.navigation.route.ProfileNavRoute
import com.keygenqt.viewer.android.features.profile.ui.screens.profile.ProfileScreen
import com.keygenqt.viewer.android.interfaces.IAppNavActions

/**
 * Actions for [ProfileScreen]
 */
interface ProfileActions : IAppNavActions {

    override val controller: NavHostController

    /**
     * To page profile
     */
    fun toProfile() {
        controller.navigate(ProfileNavRoute.profile.default.route)
    }
}
