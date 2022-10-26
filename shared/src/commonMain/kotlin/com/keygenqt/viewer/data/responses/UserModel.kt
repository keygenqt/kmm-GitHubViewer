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
 * User model
 */
@Serializable
data class UserModel(
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
    @SerialName("name")
    val name: String,
    @SerialName("company")
    val company: String?,
    @SerialName("blog")
    val blog: String?,
    @SerialName("location")
    val location: String?,
    @SerialName("email")
    val email: String?,
    @SerialName("hireable")
    val hireable: String?,
    @SerialName("bio")
    val bio: String?,
    @SerialName("twitter_username")
    val twitterUsername: String?,
    @SerialName("public_repos")
    val publicRepos: Int,
    @SerialName("public_gists")
    val publicGists: Int,
    @SerialName("followers")
    val followers: Int,
    @SerialName("following")
    val following: Int,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String,
    @SerialName("plan")
    val plan: UserPlanModel,
)

/**
 * User plan model
 */
@Serializable
data class UserPlanModel(
    @SerialName("name")
    val name: String,
    @SerialName("space")
    val space: Int,
    @SerialName("collaborators")
    val collaborators: Int,
    @SerialName("private_repos")
    val privateRepos: Int,
)

fun createUserModel(
    id: String,
    avatarUrl: String,
    name: String,
    publicRepos: Int,
    followers: Int,
    following: Int,
    blog: String?,
    location: String?,
    company: String?,
    bio: String?,
    createdAt: String
): UserModel {
    return UserModel(
        id = id,
        login = "",
        nodeId = "",
        avatarUrl = avatarUrl,
        gravatarId = "",
        url = "",
        htmlUrl = "",
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
        name = name,
        company = company,
        blog = blog,
        location = location,
        email = null,
        hireable = null,
        bio = bio,
        twitterUsername = null,
        publicRepos = publicRepos,
        publicGists = 0,
        followers = followers,
        following = following,
        createdAt = createdAt,
        updatedAt = "",
        plan = UserPlanModel(
            name = "free",
            space = 0,
            collaborators = 0,
            privateRepos = 0,
        ),
    )
}
