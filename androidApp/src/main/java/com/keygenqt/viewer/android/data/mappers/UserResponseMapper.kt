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
package com.keygenqt.viewer.android.data.mappers

import com.keygenqt.viewer.android.data.models.UserModel
import com.keygenqt.viewer.android.data.responses.UserResponse
import kotlinx.datetime.toLocalDateTime

/**
 * Extension for response [UserResponse]
 */
fun UserResponse.toModel(): UserModel {
    return UserModel(
        id = id.toString(),
        login = login ?: "",
        nodeId = node_id ?: "",
        avatarUrl = avatar_url ?: "",
        gravatarId = gravatar_id ?: "",
        url = url ?: "",
        htmlUrl = html_url ?: "",
        followersUrl = followers_url ?: "",
        followingUrl = following_url ?: "",
        gistsUrl = gists_url ?: "",
        starredUrl = starred_url ?: "",
        subscriptionsUrl = subscriptions_url ?: "",
        organizationsUrl = organizations_url ?: "",
        reposUrl = repos_url ?: "",
        eventsUrl = events_url ?: "",
        receivedEventsUrl = received_events_url ?: "",
        type = type ?: "",
        siteAdmin = site_admin ?: false,
        name = name ?: "",
        company = company ?: "",
        blog = blog ?: "",
        location = location ?: "",
        email = email ?: "",
        hireable = hireable ?: "",
        bio = bio ?: "",
        twitterUsername = twitter_username ?: "",
        publicRepos = public_repos,
        publicGists = public_gists,
        followers = followers,
        following = following,
        createdAt = created_at?.replace("Z", "")?.toLocalDateTime(),
        updatedAt = updated_at?.replace("Z", "")?.toLocalDateTime()
    )
}

/**
 * Extension for list response [UserResponse]
 */
fun List<UserResponse>.toModels(): List<UserModel> {
    return map { it.toModel() }
}
