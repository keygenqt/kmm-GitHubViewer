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
package com.keygenqt.viewer.android.features.repos.navigation.route.impl

import com.keygenqt.routing.NavigationRouteArgument2
import com.keygenqt.viewer.android.features.repos.ui.screens.repoUpdate.RepoUpdateScreen

/**
 * Routing for [RepoUpdateScreen]
 */
object RepoUpdateRoute {
    val default = object : NavigationRouteArgument2 {
        override val argument0: String = "id"
        override val argument1: String = "url"
        override val route: String = "RepoUpdateRouteDefault/{$argument0}/{$argument1}"
    }
}
