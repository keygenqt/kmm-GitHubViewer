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
import com.keygenqt.viewer.utils.AppConstants.Language.C
import com.keygenqt.viewer.utils.AppConstants.Language.BASH
import com.keygenqt.viewer.utils.AppConstants.Language.CPLUSPLUS
import com.keygenqt.viewer.utils.AppConstants.Language.DART
import com.keygenqt.viewer.utils.AppConstants.Language.ELIXIR
import com.keygenqt.viewer.utils.AppConstants.Language.ERLANG
import com.keygenqt.viewer.utils.AppConstants.Language.GROOVY
import com.keygenqt.viewer.utils.AppConstants.Language.HASKELL
import com.keygenqt.viewer.utils.AppConstants.Language.JAVA
import com.keygenqt.viewer.utils.AppConstants.Language.JAVASCRIPT
import com.keygenqt.viewer.utils.AppConstants.Language.KOTLIN
import com.keygenqt.viewer.utils.AppConstants.Language.PHP
import com.keygenqt.viewer.utils.AppConstants.Language.PYTHON
import com.keygenqt.viewer.utils.AppConstants.Language.RUBY
import com.keygenqt.viewer.utils.AppConstants.Language.RUST
import com.keygenqt.viewer.utils.AppConstants.Language.SCALA
import com.keygenqt.viewer.utils.AppConstants.Language.SWIFT
import java.util.*

/**
 * Image with language icon
 */
@Composable
fun LanguageImage(
    language: String,
) {
    val resId = when (language.lowercase(Locale.getDefault())) {
        SWIFT -> R.drawable.ic_pl_swift_plain
        BASH -> R.drawable.ic_pl_bash_plain
        C -> R.drawable.ic_pl_c_plain
        CPLUSPLUS -> R.drawable.ic_pl_cplusplus_plain
        DART -> R.drawable.ic_pl_dart_plain
        ELIXIR -> R.drawable.ic_pl_elixir_plain
        ERLANG -> R.drawable.ic_pl_erlang_plain
        GROOVY -> R.drawable.ic_pl_groovy_plain
        HASKELL -> R.drawable.ic_pl_haskell_plain
        JAVA -> R.drawable.ic_pl_java_plain
        JAVASCRIPT -> R.drawable.ic_pl_javascript_plain
        KOTLIN -> R.drawable.ic_pl_kotlin_plain
        PHP -> R.drawable.ic_pl_php_plain
        PYTHON -> R.drawable.ic_pl_python_plain
        RUBY -> R.drawable.ic_pl_ruby_plain
        RUST -> R.drawable.ic_pl_rust_plain
        SCALA -> R.drawable.ic_pl_scala_plain
        else -> R.drawable.ic_github_original
    }

    val padding = when (language.lowercase(Locale.getDefault())) {
        BASH -> 0.dp
        C -> 0.dp
        CPLUSPLUS -> 0.dp
        DART -> 0.dp
        ELIXIR -> 0.dp
        ERLANG -> 0.dp
        GROOVY -> 0.dp
        HASKELL -> 0.dp
        JAVA -> 0.dp
        JAVASCRIPT -> 2.dp
        KOTLIN -> 2.dp
        PHP -> 0.dp
        PYTHON -> 0.dp
        RUBY -> 0.dp
        RUST -> 0.dp
        SCALA -> 0.dp
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
        LanguageImage(SWIFT)
    }
}
