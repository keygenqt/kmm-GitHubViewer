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
package com.keygenqt.viewer.android.features.profile.navigation.actions.impl

import androidx.navigation.NavHostController
import com.keygenqt.viewer.android.features.profile.navigation.nav.ProfileNav
import com.keygenqt.viewer.android.features.profile.ui.screens.profileMain.ProfileMainScreen
import com.keygenqt.viewer.android.interfaces.IAppNavActions

/**
 * Actions for [ProfileMainScreen]
 */
interface ProfileMainActions: IAppNavActions {

    override val controller: NavHostController

    /**
     * To main page followers
     */
    fun toProfileMain(popUpTo: String? = null) {
        controller.navigate(ProfileNav.navProfileMain.profileMainScreen.route) {
            popUpTo?.let {
                popUpTo(popUpTo) { inclusive = true }
            }
        }
    }
}
