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
package com.keygenqt.viewer.android.features.repos.ui.screens.repo

import android.content.res.Configuration
import android.text.format.Formatter
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.keygenqt.modifier.graphicsCollapse
import com.keygenqt.viewer.android.R
import com.keygenqt.viewer.android.compose.texts.TextBodyMedium
import com.keygenqt.viewer.android.compose.texts.TextLabelLarge
import com.keygenqt.viewer.android.compose.texts.TextLabelSmall
import com.keygenqt.viewer.android.data.mock.mock
import com.keygenqt.viewer.android.data.models.RepoModel
import com.keygenqt.viewer.android.extensions.capitalize
import com.keygenqt.viewer.android.extensions.formatDate
import com.keygenqt.viewer.android.features.profile.ui.actions.ProfileActions
import com.keygenqt.viewer.android.theme.AppTheme

@Composable
fun RepoInfo(
    model: RepoModel,
    lazyListState: LazyListState = rememberLazyListState(),
    onActions: (ProfileActions) -> Unit = {},
) {
    LazyColumn(
        modifier = Modifier,
        state = lazyListState
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(bottomStart = 50.dp),
                        clip = true
                    )
                    .clip(RoundedCornerShape(bottomStart = 50.dp))
                    .background(MaterialTheme.colorScheme.primary)
                    .graphicsCollapse(lazyListState)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(
                            elevation = 8.dp,
                            shape = RoundedCornerShape(bottomStart = 50.dp),
                            clip = true
                        )
                        .clip(RoundedCornerShape(bottomStart = 50.dp))
                        .background(MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Box(
                        modifier = Modifier
                    ) {
                        Row(
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(24.dp)
                                .padding(bottom = 4.dp)
                        ) {
                            Text(
                                text = model.stargazersCount.toString(),
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                fontWeight = FontWeight.Bold,
                                fontSize = 60.sp
                            )
                            Spacer(modifier = Modifier.size(14.dp))
                            Column(
                                modifier = Modifier.padding(top = 20.dp)
                            ) {
                                Text(
                                    text = "Star",
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp
                                )
                                Text(
                                    text = "Positive ratings",
                                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f),
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 14.sp
                                )
                            }
                        }
                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.BottomStart),
                            contentScale = ContentScale.FillWidth,
                            painter = painterResource(R.drawable.chart_1),
                            contentDescription = null,
                        )
                    }
                }
                Box(
                    modifier = Modifier
                ) {
                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(24.dp)
                            .padding(bottom = 4.dp)
                    ) {
                        Text(
                            text = model.forksCount.toString(),
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 60.sp
                        )
                        Spacer(modifier = Modifier.size(14.dp))
                        Column(
                            modifier = Modifier.padding(top = 20.dp)
                        ) {
                            Text(
                                text = "Forks",
                                color = MaterialTheme.colorScheme.onPrimary,
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp
                            )
                            Text(
                                text = "Copy of a repository",
                                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f),
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp
                            )
                        }
                    }
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomStart),
                        contentScale = ContentScale.FillWidth,
                        painter = painterResource(R.drawable.chart_2),
                        contentDescription = null,
                    )
                }
            }
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                InfoBlockCount(
                    label = stringResource(id = R.string.repo_open_issue),
                    vectorImage = Icons.Default.BugReport,
                    count = model.openIssuesCount.toString()
                )
                InfoBlockCount(
                    label = stringResource(id = R.string.repo_open_watchers),
                    vectorImage = Icons.Default.Visibility,
                    count = model.watchersCount.toString()
                )
                InfoBlockCount(
                    label = stringResource(id = R.string.repo_label_size),
                    vectorImage = Icons.Default.Storage,
                    count = Formatter.formatShortFileSize(
                        LocalContext.current,
                        model.size.toLong() * 10000
                    )
                )
            }
        }
        item {
            Column {
                Spacer(modifier = Modifier.size(6.dp))

                Column(
                    modifier = Modifier.background(MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Spacer(modifier = Modifier.size(20.dp))

                    InfoBlock(
                        label = stringResource(id = R.string.repo_label_full_name),
                        text = model.fullName
                    )
                    InfoBlock(
                        label = stringResource(id = R.string.repo_label_license),
                        text = model.license["name"] ?: ""
                    )
                    InfoBlock(
                        label = stringResource(id = R.string.repo_label_visibility),
                        text = model.visibility.name.capitalize()
                    )
                    InfoBlock(
                        label = stringResource(id = R.string.repo_label_owner),
                        text = model.owner?.ownerLogin ?: ""
                    )

                    InfoBlock(
                        label = stringResource(id = R.string.repo_label_updated_at),
                        text = model.updatedAt.formatDate()
                    )
                    InfoBlock(
                        label = stringResource(id = R.string.repo_label_created_at),
                        text = model.createdAt.formatDate()
                    )
                    InfoBlock(
                        label = stringResource(id = R.string.repo_label_description),
                        text = model.description
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                }
            }
        }
    }
}

@Composable
fun InfoBlockCount(
    label: String,
    vectorImage: ImageVector,
    count: String? = null
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextLabelSmall(text = label.uppercase())
        Spacer(modifier = Modifier.size(2.dp))
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(26.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(top = 10.dp, bottom = 10.dp, start = 28.dp, end = 28.dp)
        ) {
            Icon(
                imageVector = vectorImage,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
        Spacer(modifier = Modifier.size(8.dp))
        TextLabelLarge(text = count.toString())
    }
}

@Composable
fun InfoBlock(
    label: String,
    text: String
) {
    if (text.isNotEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Spacer(modifier = Modifier.size(10.dp))
            TextLabelLarge(text = label)
            Spacer(modifier = Modifier.size(2.dp))
            TextBodyMedium(text = text)
            Spacer(modifier = Modifier.size(10.dp))
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PIXEL_4)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.PIXEL_4)
@Composable
private fun Preview() {
    AppTheme {
        RepoInfo(RepoModel.mock())
    }
}
