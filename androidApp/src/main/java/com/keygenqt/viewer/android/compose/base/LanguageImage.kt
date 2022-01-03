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
import com.keygenqt.viewer.android.utils.ConstantsLanguage.LANGUAGE_BASH
import com.keygenqt.viewer.android.utils.ConstantsLanguage.LANGUAGE_C
import com.keygenqt.viewer.android.utils.ConstantsLanguage.LANGUAGE_CPLUSPLUS
import com.keygenqt.viewer.android.utils.ConstantsLanguage.LANGUAGE_DART
import com.keygenqt.viewer.android.utils.ConstantsLanguage.LANGUAGE_ELIXIR
import com.keygenqt.viewer.android.utils.ConstantsLanguage.LANGUAGE_ERLANG
import com.keygenqt.viewer.android.utils.ConstantsLanguage.LANGUAGE_GROOVY
import com.keygenqt.viewer.android.utils.ConstantsLanguage.LANGUAGE_HASKELL
import com.keygenqt.viewer.android.utils.ConstantsLanguage.LANGUAGE_JAVA
import com.keygenqt.viewer.android.utils.ConstantsLanguage.LANGUAGE_JAVASCRIPT
import com.keygenqt.viewer.android.utils.ConstantsLanguage.LANGUAGE_KOTLIN
import com.keygenqt.viewer.android.utils.ConstantsLanguage.LANGUAGE_PHP
import com.keygenqt.viewer.android.utils.ConstantsLanguage.LANGUAGE_PYTHON
import com.keygenqt.viewer.android.utils.ConstantsLanguage.LANGUAGE_RUBY
import com.keygenqt.viewer.android.utils.ConstantsLanguage.LANGUAGE_RUST
import com.keygenqt.viewer.android.utils.ConstantsLanguage.LANGUAGE_SCALA
import com.keygenqt.viewer.android.utils.ConstantsLanguage.LANGUAGE_SWIFT
import java.util.*

/**
 * Image with language icon
 */
@Composable
fun LanguageImage(
    language: String,
) {
    val resId = when (language.lowercase(Locale.getDefault())) {
        LANGUAGE_SWIFT -> R.drawable.ic_pl_swift_plain
        LANGUAGE_BASH -> R.drawable.ic_pl_bash_plain
        LANGUAGE_C -> R.drawable.ic_pl_c_plain
        LANGUAGE_CPLUSPLUS -> R.drawable.ic_pl_cplusplus_plain
        LANGUAGE_DART -> R.drawable.ic_pl_dart_plain
        LANGUAGE_ELIXIR -> R.drawable.ic_pl_elixir_plain
        LANGUAGE_ERLANG -> R.drawable.ic_pl_erlang_plain
        LANGUAGE_GROOVY -> R.drawable.ic_pl_groovy_plain
        LANGUAGE_HASKELL -> R.drawable.ic_pl_haskell_plain
        LANGUAGE_JAVA -> R.drawable.ic_pl_java_plain
        LANGUAGE_JAVASCRIPT -> R.drawable.ic_pl_javascript_plain
        LANGUAGE_KOTLIN -> R.drawable.ic_pl_kotlin_plain
        LANGUAGE_PHP -> R.drawable.ic_pl_php_plain
        LANGUAGE_PYTHON -> R.drawable.ic_pl_python_plain
        LANGUAGE_RUBY -> R.drawable.ic_pl_ruby_plain
        LANGUAGE_RUST -> R.drawable.ic_pl_rust_plain
        LANGUAGE_SCALA -> R.drawable.ic_pl_scala_plain
        else -> R.drawable.ic_github_original
    }

    val padding = when (language.lowercase(Locale.getDefault())) {
        LANGUAGE_BASH -> 0.dp
        LANGUAGE_C -> 0.dp
        LANGUAGE_CPLUSPLUS -> 0.dp
        LANGUAGE_DART -> 0.dp
        LANGUAGE_ELIXIR -> 0.dp
        LANGUAGE_ERLANG -> 0.dp
        LANGUAGE_GROOVY -> 0.dp
        LANGUAGE_HASKELL -> 0.dp
        LANGUAGE_JAVA -> 0.dp
        LANGUAGE_JAVASCRIPT -> 2.dp
        LANGUAGE_KOTLIN -> 2.dp
        LANGUAGE_PHP -> 0.dp
        LANGUAGE_PYTHON -> 0.dp
        LANGUAGE_RUBY -> 0.dp
        LANGUAGE_RUST -> 0.dp
        LANGUAGE_SCALA -> 0.dp
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
        LanguageImage(LANGUAGE_SWIFT)
    }
}
