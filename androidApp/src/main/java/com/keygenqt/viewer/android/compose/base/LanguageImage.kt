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

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keygenqt.viewer.android.R
import com.keygenqt.viewer.android.theme.AppTheme
import com.keygenqt.viewer.utils.AppConstants.LANGUAGE
import java.util.*

/**
 * Image with language icon
 */
@Composable
fun LanguageImage(
    language: String,
) {
    val resId = when (language.lowercase(Locale.getDefault())) {
        LANGUAGE.SWIFT -> R.drawable.ic_pl_swift_plain
        LANGUAGE.BASH -> R.drawable.ic_pl_bash_plain
        LANGUAGE.C -> R.drawable.ic_pl_c_plain
        LANGUAGE.CPLUSPLUS -> R.drawable.ic_pl_cplusplus_plain
        LANGUAGE.DART -> R.drawable.ic_pl_dart_plain
        LANGUAGE.ELIXIR -> R.drawable.ic_pl_elixir_plain
        LANGUAGE.ERLANG -> R.drawable.ic_pl_erlang_plain
        LANGUAGE.GROOVY -> R.drawable.ic_pl_groovy_plain
        LANGUAGE.HASKELL -> R.drawable.ic_pl_haskell_plain
        LANGUAGE.JAVA -> R.drawable.ic_pl_java_plain
        LANGUAGE.JAVASCRIPT -> R.drawable.ic_pl_javascript_plain
        LANGUAGE.KOTLIN -> R.drawable.ic_pl_kotlin_plain
        LANGUAGE.PHP -> R.drawable.ic_pl_php_plain
        LANGUAGE.PYTHON -> R.drawable.ic_pl_python_plain
        LANGUAGE.RUBY -> R.drawable.ic_pl_ruby_plain
        LANGUAGE.RUST -> R.drawable.ic_pl_rust_plain
        LANGUAGE.SCALA -> R.drawable.ic_pl_scala_plain
        else -> R.drawable.ic_github_original
    }

    val padding = when (language.lowercase(Locale.getDefault())) {
        LANGUAGE.BASH -> 0.dp
        LANGUAGE.C -> 0.dp
        LANGUAGE.CPLUSPLUS -> 0.dp
        LANGUAGE.DART -> 0.dp
        LANGUAGE.ELIXIR -> 0.dp
        LANGUAGE.ERLANG -> 0.dp
        LANGUAGE.GROOVY -> 0.dp
        LANGUAGE.HASKELL -> 0.dp
        LANGUAGE.JAVA -> 0.dp
        LANGUAGE.JAVASCRIPT -> 2.dp
        LANGUAGE.KOTLIN -> 2.dp
        LANGUAGE.PHP -> 0.dp
        LANGUAGE.PYTHON -> 0.dp
        LANGUAGE.RUBY -> 0.dp
        LANGUAGE.RUST -> 0.dp
        LANGUAGE.SCALA -> 0.dp
        else -> 0.dp
    }

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .size(36.dp)
            .background(MaterialTheme.colorScheme.onSurface)
    ) {
        Image(
            modifier = Modifier
                .size(22.dp)
                .padding(padding)
                .align(Alignment.Center),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.surface),
            painter = painterResource(resId),
            contentDescription = null,
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PIXEL_4)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.PIXEL_4)
@Composable
private fun Preview() {
    AppTheme {
        LanguageImage(LANGUAGE.SWIFT)
    }
}
