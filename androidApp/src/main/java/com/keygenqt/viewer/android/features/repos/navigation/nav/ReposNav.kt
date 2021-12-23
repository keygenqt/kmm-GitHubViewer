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
package com.keygenqt.viewer.android.features.repos.navigation.nav

import com.keygenqt.viewer.android.features.repos.navigation.nav.impl.NavRepoScreen
import com.keygenqt.viewer.android.features.repos.navigation.nav.impl.NavRepoUpdateScreen
import com.keygenqt.viewer.android.features.repos.navigation.nav.impl.NavReposListScreen

/**
 * Base routing for feature
 */
object ReposNav {
    val navRepo = NavRepoScreen
    val navReposList = NavReposListScreen
    val navRepoUpdate = NavRepoUpdateScreen
}
