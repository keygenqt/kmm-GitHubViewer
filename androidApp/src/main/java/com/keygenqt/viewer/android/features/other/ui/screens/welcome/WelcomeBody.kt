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
package com.keygenqt.viewer.android.features.other.ui.screens.welcome

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Card
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keygenqt.viewer.android.BuildConfig
import com.keygenqt.viewer.android.R
import com.keygenqt.viewer.android.base.viewModel.AppViewModel
import com.keygenqt.viewer.android.compose.components.AppScaffold
import com.keygenqt.viewer.android.compose.texts.TextBodySmall
import com.keygenqt.viewer.android.compose.texts.TextDisplayMedium
import com.keygenqt.viewer.android.features.other.ui.actions.WelcomeActions
import com.keygenqt.viewer.android.theme.AppTheme

@Composable
fun WelcomeBody(
    appViewModel: AppViewModel? = null,
    onActions: (WelcomeActions) -> Unit = {},
) {
    AppScaffold {
        Box {
            Image(
                painterResource(id = if (isSystemInDarkTheme()) R.drawable.bg_welcome_dark else R.drawable.bg_welcome),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Bottom,
                ) {
                    TextDisplayMedium(
                        modifier = Modifier,
                        text = stringResource(id = R.string.welcome_title)
                    )

                    Spacer(modifier = Modifier.size(4.dp))

                    WelcomeBodySubtitle(
                        text = stringResource(id = R.string.welcome_subtitle)
                    )
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Card(
                        elevation = 10.dp,
                        shape = RoundedCornerShape(0.dp),
                        modifier = Modifier
                            .background(Color.White)
                            .height(130.dp)
                            .width(130.dp)
                            .fillMaxWidth()
                    ) {
                        WelcomeAnimation(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp)
                        )
                    }
                }

                Column(
                    modifier = Modifier
                ) {
                    Button(
                        contentPadding = PaddingValues(
                            start = 20.dp,
                            top = 22.dp,
                            end = 20.dp,
                            bottom = 18.dp
                        ),
                        onClick = {
                            onActions(WelcomeActions.ToSignIn)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(40.dp),
                    ) {
                        Text(
                            style = MaterialTheme.typography.headlineSmall,
                            text = stringResource(id = R.string.welcome_btn_sign_in)
                        )
                    }

                    Spacer(modifier = Modifier.size(16.dp))

                    TextBodySmall(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End,
                        text = stringResource(
                            id = R.string.welcome_version,
                            BuildConfig.VERSION_NAME
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun WelcomeBodySubtitle(
    text: String,
    color: Color = MaterialTheme.colorScheme.onBackground
) {
    val uriHandler = LocalUriHandler.current
    val addLinkAnnotation: AnnotatedString.Builder.(String, String) -> Unit = { value, link ->
        val start = text.indexOf(value)
        addStyle(
            style = SpanStyle(textDecoration = TextDecoration.Underline),
            start = start,
            end = start + value.length
        )
        addStringAnnotation(
            tag = "URL",
            annotation = link,
            start = start,
            end = start + value.length
        )
    }

    val annotatedLinkString: AnnotatedString = buildAnnotatedString {
        append(text)
        addStyle(
            style = SpanStyle(color = color),
            start = 0,
            end = text.length
        )
        addLinkAnnotation("KMM", "https://kotlinlang.org/docs/kmm-overview.html")
        addLinkAnnotation(
            "MVVM",
            "https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel"
        )
        addLinkAnnotation("Jetpack Compose", "https://developer.android.com/jetpack/compose")
        addLinkAnnotation("SwiftUI", "https://developer.apple.com/xcode/swiftui/")
    }

    ClickableText(
        modifier = Modifier.fillMaxWidth(),
        text = annotatedLinkString,
        style = MaterialTheme.typography.titleMedium,
        onClick = {
            annotatedLinkString
                .getStringAnnotations("URL", it, it)
                .firstOrNull()?.let { stringAnnotation ->
                    uriHandler.openUri(stringAnnotation.item)
                }
        }
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PIXEL_4)
@Composable
private fun Preview() {
    AppTheme {
        WelcomeBody()
    }
}
