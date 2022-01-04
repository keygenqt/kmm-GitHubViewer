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
package com.keygenqt.viewer.android.features.repos.ui.actions

import android.net.Uri
import com.keygenqt.viewer.android.features.repos.ui.screens.repos.ReposScreen

/**
 * Actions sealed class for screen [ReposScreen]
 */
sealed class ReposActions {

    /**
     * Sort repos
     */
    object SortToggle : ReposActions()

    /**
     * Top repo view page
     */
    data class ToRepoView(val id: String, val url: Uri) : ReposActions()
}
