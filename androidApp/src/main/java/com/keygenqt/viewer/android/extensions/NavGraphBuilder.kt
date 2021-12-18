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
package com.keygenqt.viewer.android.extensions

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable

/**
 * Animation navigator app
 */
@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.composableAnimation(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    enterTransition: AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition? = {
        fadeIn(animationSpec = tween(500))
    },
    exitTransition: (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?)? = {
        fadeOut(animationSpec = tween(1000))
    },
    popEnterTransition: AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition? = {
        fadeIn(animationSpec = tween(500))
    },
    popExitTransition: (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?)? = {
        fadeOut(animationSpec = tween(1000))
    },
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit
) = composable(
    route = route,
    arguments = arguments,
    deepLinks = deepLinks,
    enterTransition = enterTransition,
    exitTransition = exitTransition,
    popEnterTransition = popEnterTransition,
    popExitTransition = popExitTransition,
    content = content,
)
