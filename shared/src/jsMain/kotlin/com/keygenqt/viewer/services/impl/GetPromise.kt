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
package com.keygenqt.viewer.services.impl

import com.keygenqt.viewer.services.AppHttpClient
import com.keygenqt.viewer.utils.wrapPromise
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*

@JsExport
@Suppress("NON_EXPORTABLE_TYPE")
class GetPromise(private val client: AppHttpClient) {

    /**
     * Test query without tokens from demo KM
     */
    fun rockets() = wrapPromise {
        client.get.rockets()
    }

    /**
     * Get list repos user
     */
    fun repos(
        page: Int = 1,
        isSortASC: Boolean = false,
    ) = wrapPromise {
        client.get.repos(page, isSortASC)
    }

    /**
     * Get repo data by url
     */
    fun repo(
        url: String
    ) = wrapPromise {
        client.get.repo(url)
    }

    /**
     * Get list user followers
     */
    fun followers(
        page: Int = 1
    ) = wrapPromise {
        client.get.followers(page)
    }

    /**
     * Get user
     */
    fun user() = wrapPromise {
        client.get.user()
    }
}
