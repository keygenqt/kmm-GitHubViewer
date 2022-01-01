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
package com.keygenqt.viewer.android.features.other.ui.screens.onboarding

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.keygenqt.viewer.android.compose.base.AppScaffold
import com.keygenqt.viewer.android.compose.texts.TextLabelLarge
import com.keygenqt.viewer.android.extensions.disableHorizontalPointerInputScroll
import com.keygenqt.viewer.android.features.other.ui.actions.OnboardingActions
import com.keygenqt.viewer.android.interfaces.IPagerState
import com.keygenqt.viewer.android.theme.AppTheme

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingBody(
    onActions: (OnboardingActions) -> Unit = {},
) {
    val pages = object : IPagerState {

        // pages
        val step1 = 0
        val step2 = 1
        val step3 = 2

        // override
        override val scope = rememberCoroutineScope()
        override val state = rememberPagerState()
        override val count = 3
    }

    val done = { onActions(OnboardingActions.DoneOnboarding) }

    AppScaffold(
        topBarTitle = if (pages.isFirst) "Hello!" else "",
        topBarActions = {
            IconButton(onClick = done) {
                TextLabelLarge(text = "Skip")
            }
        },
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            HorizontalPager(
                modifier = Modifier.disableHorizontalPointerInputScroll(),
                state = pages.state,
                count = pages.count
            ) { page ->
                when (page) {
                    pages.step1 -> OnboardingStep1()
                    pages.step2 -> OnboardingStep1()
                    pages.step3 -> OnboardingStep1()
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
                    .align(Alignment.BottomCenter),
            ) {

                CircularProgressIndicator(
                    strokeWidth = 1.dp,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(61.dp),
                    progress = 1 - pages.progress
                )

                FloatingActionButton(
                    modifier = Modifier.align(Alignment.Center),
                    onClick = {
                        pages.next(done)
                    },
                    backgroundColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ) {
                    Icon(
                        modifier = Modifier.padding(start = 2.dp),
                        imageVector = Icons.Filled.ArrowForwardIos,
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PIXEL_4)
@Composable
private fun Preview() {
    AppTheme {
        OnboardingBody()
    }
}
