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

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import com.keygenqt.viewer.android.extensions.AddListenChangeNavigation

/**
 * For listen change route
 */
class ListenDestination {
    companion object {

        private var ACTION_DESTINATION: MutableList<NavDestination> = mutableListOf()

        /**
         * Init ListenDestination listen change navigation
         */
        @Composable
        fun Init(controller: NavHostController, onChange: (destination: NavDestination) -> Unit) {
            controller.AddListenChangeNavigation {
                ACTION_DESTINATION.add(it)
                onChange.invoke(it)
            }
        }

        /**
         * Get last destination
         */
        fun getActionDestination(): NavDestination? {
            return ACTION_DESTINATION.lastOrNull()
        }

        /**
         * Clear stack navigation
         */
        fun clearStack(backPressedDispatcher: OnBackPressedDispatcher): String {
            while (backPressedDispatcher.hasEnabledCallbacks()) {
                backPressedDispatcher.onBackPressed()
            }
            return clear()?.route ?: ConstantsApp.START_DESTINATION
        }

        /**
         * Clear data
         */
        private fun clear(): NavDestination? {
            val last = ACTION_DESTINATION.firstOrNull()
            ACTION_DESTINATION.clear()
            return last
        }
    }
}
