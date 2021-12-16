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
package com.keygenqt.viewer.android.features.followers.ui.screens.followersMain

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.keygenqt.viewer.android.compose.components.AppImageUser
import com.keygenqt.viewer.android.compose.texts.TextBodySmall
import com.keygenqt.viewer.android.compose.texts.TextTitleLarge
import com.keygenqt.viewer.android.data.models.FollowerModel
import com.keygenqt.viewer.android.theme.AppTheme
import androidx.compose.material.MaterialTheme as MaterialTheme2

@OptIn(ExperimentalCoilApi::class)
@Composable
fun FollowerItem(
    index: Int,
    model: FollowerModel,
    uriHandler: UriHandler? = null,
) {
    Box(
        modifier = Modifier
    ) {

        Card(
            shape = MaterialTheme2.shapes.medium,
            backgroundColor = MaterialTheme.colorScheme.surface,
            elevation = 12.dp,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .padding(top = 20.dp, bottom = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .clickable(onClick = {
                        uriHandler?.openUri(model.htmlUrl)
                    })
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 24.dp, bottom = 16.dp, end = 16.dp, start = 16.dp)
                ) {
                    TextTitleLarge(
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.onSurface,
                        text = model.login
                    )
                }
            }
        }

        Card(
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.TopCenter),
            shape = CircleShape,
            backgroundColor = MaterialTheme.colorScheme.surface,
            elevation = 12.dp,
        ) {
            Box(
                modifier = Modifier.padding(3.dp)
            ) {
                AppImageUser(
                    url = model.avatarUrl,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape),
                )
            }
        }
    }

}

@Composable
private fun ReposItemCount(
    modifier: Modifier = Modifier,
    vectorImage: ImageVector,
    count: Int? = null
) {
    Row(modifier = modifier) {
        if (count != 0) {
            Box(
                modifier = Modifier.size(18.dp),
            ) {
                Icon(
                    modifier = Modifier
                        .padding(bottom = 2.dp)
                        .size(14.dp)
                        .align(Alignment.Center),
                    imageVector = vectorImage,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
        if (count != null && count != 0) {
            Box(
                modifier = Modifier.size(18.dp),
            ) {
                TextBodySmall(
                    modifier = Modifier
                        .align(Alignment.Center),
                    color = MaterialTheme.colorScheme.onSurface,
                    text = count.toString()
                )
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PIXEL_4)
@Composable
private fun Preview() {
    AppTheme {
//        ReposItem()
    }
}
