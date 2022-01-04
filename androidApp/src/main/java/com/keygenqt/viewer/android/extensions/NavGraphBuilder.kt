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
package com.keygenqt.viewer.android.extensions

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect

/**
 * States animation
 */
enum class AnimatedNavGraphState {
    START,
    END
}

/**
 * SharedFlow state animation
 */
private val stateAnimation: MutableSharedFlow<AnimatedNavGraphState> = MutableSharedFlow(
    replay = 1,
    onBufferOverflow = BufferOverflow.DROP_OLDEST
)

/**
 * LaunchedEffectAnimation for state animation
 */
@Composable
fun LaunchedEffectAnimation(
    block: suspend CoroutineScope.(AnimatedNavGraphState) -> Unit
) {
    LaunchedEffect(Unit) {
        stateAnimation.collect {
            block.invoke(this, it)
        }
    }
}

/**
 * Animation navigator app
 */
@OptIn(ExperimentalAnimationApi::class, ExperimentalComposeUiApi::class)
fun NavGraphBuilder.composableAnimation(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    enterTransition: AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition? = {
        fadeIn(animationSpec = tween(500))
    },
    exitTransition: (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?)? = {
        fadeOut(animationSpec = tween(500))
    },
    popEnterTransition: AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition? = {
        fadeIn(animationSpec = tween(500))
    },
    popExitTransition: (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?)? = {
        fadeOut(animationSpec = tween(500))
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
) {
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    val softwareKeyboardController = LocalSoftwareKeyboardController.current
    val localFocusManager = LocalFocusManager.current
    content.invoke(this, it)
    DisposableEffect(lifecycleOwner) {
        stateAnimation.tryEmit(AnimatedNavGraphState.START)
        onDispose {
            localFocusManager.clearFocus()
            softwareKeyboardController?.hide()
            stateAnimation.tryEmit(AnimatedNavGraphState.END)
        }
    }
}
