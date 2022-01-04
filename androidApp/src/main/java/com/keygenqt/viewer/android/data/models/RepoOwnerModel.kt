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
package com.keygenqt.viewer.android.data.models

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import kotlinx.serialization.Serializable

/**
 * Repo owner model
 */
@Entity
@Immutable
@Serializable
data class RepoOwnerModel(
    val ownerId: String,
    val ownerLogin: String? = null,
    val ownerNodeId: String? = null,
    val ownerAvatarUrl: String? = null,
    val ownerGravatarId: String? = null,
    val ownerUrl: String? = null,
    val ownerHtmlUrl: String? = null,
    val ownerFollowersUrl: String? = null,
    val ownerFollowingUrl: String? = null,
    val ownerGistsUrl: String? = null,
    val ownerStarredUrl: String? = null,
    val ownerSubscriptionsUrl: String? = null,
    val ownerOrganizationsUrl: String? = null,
    val ownerReposUrl: String? = null,
    val ownerEventsUrl: String? = null,
    val ownerReceivedEventsUrl: String? = null,
    val ownerType: String? = null,
    val ownerSiteAdmin: Boolean? = null,
)
