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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keygenqt.viewer.android.compose.components.LazyColumnCollapse
import com.keygenqt.viewer.android.compose.texts.TextLabelLarge
import com.keygenqt.viewer.android.compose.texts.TextTitleLarge
import com.keygenqt.viewer.android.data.mock.mock
import com.keygenqt.viewer.android.data.models.RepoModel
import com.keygenqt.viewer.android.features.profile.ui.actions.ProfileMainActions
import com.keygenqt.viewer.android.theme.AppTheme

@Composable
fun RepoInfo(
    model: RepoModel,
    lazyListState: LazyListState = rememberLazyListState(),
    onActions: (ProfileMainActions) -> Unit = {},
) {
    LazyColumnCollapse(
        lazyListState = lazyListState,
        contentTop = {
            Column(
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth(),
            ) {
                TextTitleLarge(text = model.name)
            }
        }
    ) {
        (0..20).forEach {
            item {
                TextLabelLarge(text = model.description)
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PIXEL_4)
@Composable
private fun Preview() {
    AppTheme {
        RepoInfo(RepoModel.mock())
    }
}
