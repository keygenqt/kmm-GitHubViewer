package com.keygenqt.viewer.android.compose.components

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
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.keygenqt.viewer.android.R
import com.keygenqt.viewer.android.compose.components.lottie.LoadingBlockAnimation

@OptIn(ExperimentalCoilApi::class)
@Composable
fun AppImageUser(
    url: String,
    modifier: Modifier = Modifier,
) {
    val painter = rememberImagePainter(url)

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
            is ImagePainter.State.Loading -> {
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
            is ImagePainter.State.Error -> {
                Image(
                    painter = painterResource(R.drawable.user_default),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            is ImagePainter.State.Empty -> {}
            is ImagePainter.State.Success -> {}
        }
    }
}