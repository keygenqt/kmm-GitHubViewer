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
package com.keygenqt.viewer.android.compose.base

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.keygenqt.viewer.android.R
import com.keygenqt.viewer.android.compose.lottie.LoadingBlockAnimation

/**
 * Image user with default image
 */
@Composable
fun AppImageUser(
    url: String,
    modifier: Modifier = Modifier,
) {
    val painter = rememberAsyncImagePainter(url)

    Box(
        modifier = modifier
    ) {

        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            painter = painter,
        )

        when (painter.state) {
            is AsyncImagePainter.State.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0XFFC5C5C5))
                ) {
                    LoadingBlockAnimation(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            is AsyncImagePainter.State.Error -> {
                Image(
                    painter = painterResource(R.drawable.user_default),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            is AsyncImagePainter.State.Empty -> {}
            is AsyncImagePainter.State.Success -> {}
        }
    }
}
