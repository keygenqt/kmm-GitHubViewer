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
package com.keygenqt.viewer.android

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver.OnPreDrawListener
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.CompositionLocalProvider
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.keygenqt.viewer.android.base.LocalBackPressedDispatcher
import com.keygenqt.viewer.android.base.LocalViewModel
import com.keygenqt.viewer.android.base.viewModel.AppViewModel
import com.keygenqt.viewer.android.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    /**
     * Main ViewModel which is available throughout the application as staticCompositionLocalOf
     */
    private val viewModel: AppViewModel by viewModels()

    /**
     * Main initialization point of view
     */
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Disable window decor fitting
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // compose initialization
        setContent {
            CompositionLocalProvider(
                LocalViewModel provides viewModel,
                LocalBackPressedDispatcher provides this.onBackPressedDispatcher
            ) {
                AppTheme {
                    ProvideWindowInsets {
                        NavGraph(rememberAnimatedNavController())
                    }
                }
            }
        }

        // Splash delay
        window.decorView.findViewById<View>(android.R.id.content)?.let { content ->
            content.viewTreeObserver.addOnPreDrawListener(
                object : OnPreDrawListener {
                    override fun onPreDraw(): Boolean {
                        return if (viewModel.isSplash.value) {
                            // done splash remove listener
                            content.viewTreeObserver.removeOnPreDrawListener(this); true
                        } else false
                    }
                }
            )
        }
    }
}
