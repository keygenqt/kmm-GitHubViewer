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
package com.keygenqt.viewer.android.features.repos.navigation.actions.impl

import android.net.Uri
import androidx.navigation.NavHostController
import com.keygenqt.viewer.android.features.repos.navigation.nav.ReposNav
import com.keygenqt.viewer.android.features.repos.ui.screens.repo.RepoScreen
import com.keygenqt.viewer.android.interfaces.IAppNavActions
import java.net.URLEncoder

/**
 * Actions for [RepoScreen]
 */
interface RepoUpdateActions : IAppNavActions {

    override val controller: NavHostController

    /**
     * To repo update
     */
    fun toRepoUpdate(id: String, url: Uri, popUpTo: String? = null) {
        ReposNav.navRepoUpdate.repoUpdateScreen.apply {
            controller.navigate(
                getRoute(
                    argument0 = id,
                    argument1 = URLEncoder.encode(url.toString(), Charsets.UTF_8.name())
                )
            ) {
                popUpTo?.let {
                    popUpTo(popUpTo) { inclusive = true }
                }
            }
        }
    }
}
