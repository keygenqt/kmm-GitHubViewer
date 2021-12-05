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
package com.keygenqt.viewer.android.utils

import com.keygenqt.viewer.android.BuildConfig
import com.keygenqt.viewer.android.features.other.navigation.nav.OtherNav
import com.keygenqt.viewer.android.features.repos.navigation.nav.ReposNav

/**
 * Base Constants for App
 */
object ConstantsApp {

    /**
     * Start destination
     */
    val START_DESTINATION = OtherNav.navStartPage.startPageScreen.route

    /**
     * Api url
     */
    const val API_URL = "https://api.github.com/"

    /**
     * For simulate slow internet
     */
    const val DEBUG_DELAY = 1000L

    /**
     * For debug credential login
     */
    val DEBUG_CREDENTIAL_LOGIN get() = if (BuildConfig.DEBUG) "keygenqt" else ""
}
