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
package com.keygenqt.viewer.android.data.mock

import com.keygenqt.viewer.android.data.models.UserModel
import kotlinx.datetime.toLocalDateTime
import java.util.*

fun UserModel.Companion.mock() = UserModel(
    id = UUID.randomUUID().toString(),
    login = "",
    nodeId = "ADQ5VXNlcjk2MjU7MTQ=",
    avatarUrl = "https://avatars.githubusercontent.com/u/9665914?v=4",
    gravatarId = "",
    url = "https://api.github.com/users/keygenqt",
    htmlUrl = "https://github.com/keygenqt",
    followersUrl = "https://api.github.com/users/keygenqt/followers",
    followingUrl = "https://api.github.com/users/keygenqt/following{/other_user}",
    gistsUrl = "https://api.github.com/users/keygenqt/gists{/gist_id}",
    starredUrl = "https://api.github.com/users/keygenqt/starred{/owner}{/repo}",
    subscriptionsUrl = "https://api.github.com/users/keygenqt/subscriptions",
    organizationsUrl = "https://api.github.com/users/keygenqt/orgs",
    reposUrl = "https://api.github.com/users/keygenqt/repos",
    eventsUrl = "https://api.github.com/users/keygenqt/events{/privacy}",
    receivedEventsUrl = "https://api.github.com/users/keygenqt/received_events",
    type = "User",
    siteAdmin = false,
    name = "Vitaliy Zarubin",
    company = "",
    blog = "https://keygenqt.com",
    location = "Volgodonsk",
    email = "dev@keygenqt.com",
    hireable = "",
    bio = "",
    twitterUsername = "",
    publicRepos = 63,
    publicGists = 0,
    followers = 8,
    following = 2,
    createdAt = "2014-11-10T23:38:30".toLocalDateTime(),
    updatedAt = "2021-12-14T06:48:09".toLocalDateTime()
)
