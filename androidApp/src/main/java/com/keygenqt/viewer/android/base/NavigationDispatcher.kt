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

import androidx.activity.OnBackPressedDispatcher
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController

/**
 * Navigation dispatcher for routing
 */
class NavigationDispatcher(
    val controller: NavHostController,
    val backPressedDispatcher: OnBackPressedDispatcher
) {
    /**
     * Save start destination
     */
    private var _startDestination: NavDestination? = null

    /**
     * Save count open start destination
     */
    private var _firstDestinationCount: Int = 0

    /**
     * Save current destination
     */
    private var _currentDestination: NavDestination? = null

    /**
     * Get start destination
     */
    val startDestination get() = _startDestination

    /**
     * Get current destination
     */
    val currentDestination get() = _currentDestination

    init {
        // listen change destination
        val callback = NavController.OnDestinationChangedListener { controller, _, _ ->
            controller.currentDestination?.let { destination ->
                // add start destination
                if (_startDestination == null) {
                    _startDestination = destination
                }
                // counter open start destination
                if (_startDestination!!.route == destination.route) {
                    _firstDestinationCount += 1
                }
                // save current destination
                _currentDestination = destination
            }
        }
        // add listener
        controller.addOnDestinationChangedListener(callback)
    }

    /**
     * Check is navigation stack empty
     */
    fun hasEnabledCallbacks(): Boolean {
        return backPressedDispatcher.hasEnabledCallbacks()
    }

    /**
     * Step to back on navigation
     */
    fun onBackPressed() {
        backPressedDispatcher.onBackPressed()
    }

    /**
     * Clear stack and open screens
     */
    fun toRoutePopStack(vararg routes: () -> Unit) {
        if (routes.isNotEmpty()) {
            // clear stack
            _startDestination?.let { des ->
                (0.._firstDestinationCount).forEach { _ ->
                    controller.popBackStack(des.id, true)
                }
            }
            // clear first destination
            _startDestination = null
            // emit routes
            routes.forEach {
                it.invoke()
            }
        }
    }
}
