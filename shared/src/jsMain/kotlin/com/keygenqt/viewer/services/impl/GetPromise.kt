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

import com.keygenqt.viewer.data.responses.FollowerModel
import com.keygenqt.viewer.data.responses.RepoModel
import com.keygenqt.viewer.data.responses.RocketModel
import com.keygenqt.viewer.data.responses.UserModel
import com.keygenqt.viewer.services.AppHttpClient
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import kotlin.js.Promise

@JsExport
@Suppress("NON_EXPORTABLE_TYPE")
@OptIn(DelicateCoroutinesApi::class)
class GetPromise(private val client: AppHttpClient) {

    /**
     * Test query without tokens from demo KMM
     */
    fun rockets(): Promise<List<RocketModel>> {
        return GlobalScope.promise {
            client.get.rockets()
        }
    }

    /**
     * Get list repos user
     */
    fun repos(
        page: Int = 1,
        isSortASC: Boolean = false,
    ): Promise<List<RepoModel>> {
        return GlobalScope.promise {
            client.get.repos(
                page = page,
                isSortASC = isSortASC
            )
        }
    }

    /**
     * Get repo data by url
     */
    fun repo(
        url: String
    ): Promise<RepoModel> {
        return GlobalScope.promise {
            client.get.repo(url)
        }
    }

    /**
     * Get list user followers
     */
    fun followers(
        page: Int = 1
    ): Promise<List<FollowerModel>> {
        return GlobalScope.promise {
            client.get.followers(page)
        }
    }

    /**
     * Get user
     */
    fun user(): Promise<UserModel> {
        return GlobalScope.promise {
            client.get.user()
        }
    }
}
