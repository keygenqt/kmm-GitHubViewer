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
package com.keygenqt.viewer.android.base

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.runtime.staticCompositionLocalOf
import com.keygenqt.routing.NavigationDispatcher

/**
 * [AppViewModel] Can be used in CompositionLocalProvider to provide values.
 */
val LocalViewModel = staticCompositionLocalOf<AppViewModel> {
    error("No AppViewModel found!")
}

/**
 * [OnBackPressedDispatcher] Can be used in CompositionLocalProvider to provide values.
 */
val LocalNavigationDispatcher = staticCompositionLocalOf<NavigationDispatcher> {
    error("No Back Dispatcher provided")
}
