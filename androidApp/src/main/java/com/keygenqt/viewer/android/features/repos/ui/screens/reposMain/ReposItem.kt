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
package com.keygenqt.viewer.android.features.repos.ui.screens.reposMain

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keygenqt.viewer.android.compose.components.LanguageImage
import com.keygenqt.viewer.android.compose.texts.TextBodySmall
import com.keygenqt.viewer.android.compose.texts.TextTitleLarge
import com.keygenqt.viewer.android.compose.texts.TextTitleMedium
import com.keygenqt.viewer.android.data.models.RepoModel
import com.keygenqt.viewer.android.features.repos.ui.actions.ReposMainActions
import com.keygenqt.viewer.android.theme.AppTheme
import androidx.compose.material.MaterialTheme as MaterialTheme2

@Composable
fun ReposItem(
    index: Int,
    model: RepoModel,
    onActions: (ReposMainActions) -> Unit = {},
) {
    Card(
        shape = MaterialTheme2.shapes.medium,
        backgroundColor = MaterialTheme.colorScheme.surface,
        elevation = 12.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Box(
            modifier = Modifier.clickable(onClick = {
            })
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row {
                    LanguageImage(
                        language = model.language,
                    )

                    Spacer(modifier = Modifier.size(8.dp))

                    TextTitleLarge(
                        color = MaterialTheme.colorScheme.onSurface,
                        text = model.name
                    )
                }
                if (model.description.isNotEmpty()) {
                    Spacer(modifier = Modifier.size(8.dp))
                    TextTitleMedium(
                        color = MaterialTheme.colorScheme.onSurface,
                        text = model.description
                    )
                }

                Spacer(modifier = Modifier.size(16.dp))

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(0.5.dp)
                        .background(MaterialTheme.colorScheme.onSurface)
                )
                Spacer(modifier = Modifier.size(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        ReposItemCount(
                            vectorImage = Icons.Filled.Star,
                            count = model.stargazersCount
                        )
                        ReposItemCount(
                            vectorImage = Icons.Filled.CallSplit,
                            count = model.forks
                        )
                        ReposItemCount(
                            vectorImage = Icons.Filled.Visibility,
                            count = model.watchers
                        )
                    }
                    Column {
                        if (model.isPrivate) {
                            ReposItemCount(
                                modifier = Modifier,
                                vectorImage = Icons.Filled.Lock,
                            )
                        } else {
                            ReposItemCount(
                                modifier = Modifier,
                                vectorImage = Icons.Filled.LockOpen,
                            )
                        }
                    }
                }
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
