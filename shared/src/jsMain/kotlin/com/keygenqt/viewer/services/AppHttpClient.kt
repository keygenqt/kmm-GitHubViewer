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
package com.keygenqt.viewer.services

import com.keygenqt.viewer.services.impl.GetPromise
import com.keygenqt.viewer.services.impl.PatchPromise
import com.keygenqt.viewer.services.impl.PostPromise
import io.ktor.client.*
import io.ktor.client.engine.js.*

/**
 * Get platform JS client
 */
actual fun httpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(Js) {
    config(this)
}

/**
 * JS service network
 */
@JsExport
class AppHttpClientJS(
    private var token: String = ""
) {
    private val ktorClient = AppHttpClient(token)

    val get = GetPromise(ktorClient)
    val post = PostPromise(ktorClient)
    val patch = PatchPromise(ktorClient)
}