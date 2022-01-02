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
package com.keygenqt.viewer.android.interfaces

import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.keygenqt.viewer.android.base.NavigationDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Interface for [HorizontalPager]
 */
@OptIn(ExperimentalPagerApi::class)
interface IPagerState {

    val scope: CoroutineScope
    val state: PagerState
    val count: Int

    val progress: Float get() = state.currentPage.toFloat() / (count - 1).toFloat()
    val isFirst: Boolean get() = state.currentPage == 0

    fun next(end: () -> Unit = {}) {
        if (count > state.currentPage + 1) {
            scope.launch {
                state.animateScrollToPage(state.currentPage + 1)
            }
        } else {
            end.invoke()
        }
    }
}
