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
package com.keygenqt.viewer.data.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Follower model
 */
@Serializable
data class FollowerModel(
    @SerialName("id")
    val id: String,
    @SerialName("login")
    val login: String,
    @SerialName("node_id")
    val nodeId: String,
    @SerialName("avatar_url")
    val avatarUrl: String,
    @SerialName("gravatar_id")
    val gravatarId: String,
    @SerialName("url")
    val url: String,
    @SerialName("html_url")
    val htmlUrl: String,
    @SerialName("followers_url")
    val followersUrl: String,
    @SerialName("following_url")
    val followingUrl: String,
    @SerialName("gists_url")
    val gistsUrl: String,
    @SerialName("starred_url")
    val starredUrl: String,
    @SerialName("subscriptions_url")
    val subscriptionsUrl: String,
    @SerialName("organizations_url")
    val organizationsUrl: String,
    @SerialName("repos_url")
    val reposUrl: String,
    @SerialName("events_url")
    val eventsUrl: String,
    @SerialName("received_events_url")
    val receivedEventsUrl: String,
    @SerialName("type")
    val type: String,
    @SerialName("site_admin")
    val siteAdmin: Boolean,
)

fun createFollowerModel(
    id: String,
    login: String,
    avatarUrl: String,
    htmlUrl: String
): FollowerModel {
    return FollowerModel(
        id = id,
        login = login,
        nodeId = "",
        avatarUrl = avatarUrl,
        gravatarId = "",
        url = "",
        htmlUrl = htmlUrl,
        followersUrl = "",
        followingUrl = "",
        gistsUrl = "",
        starredUrl = "",
        subscriptionsUrl = "",
        organizationsUrl = "",
        reposUrl = "",
        eventsUrl = "",
        receivedEventsUrl = "",
        type = "",
        siteAdmin = false,
    )
}
