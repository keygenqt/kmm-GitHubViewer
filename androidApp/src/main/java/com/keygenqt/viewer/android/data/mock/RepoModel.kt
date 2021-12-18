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
package com.keygenqt.viewer.android.data.mock

import com.keygenqt.viewer.android.data.models.RepoModel
import com.keygenqt.viewer.android.data.models.RepoOwnerModel
import kotlinx.datetime.toLocalDateTime
import java.util.*

fun RepoModel.Companion.mock() = RepoModel(
    id = UUID.randomUUID().toString(),
    name = "",
    fullName = "",
    owner = RepoOwnerModel(
        ownerId = UUID.randomUUID().toString(),
        ownerLogin = "",
        ownerNodeId = "",
        ownerAvatarUrl = "",
        ownerGravatarId = "",
        ownerUrl = "",
        ownerHtmlUrl = "",
        ownerFollowersUrl = "",
        ownerFollowingUrl = "",
        ownerGistsUrl = "",
        ownerStarredUrl = "",
        ownerSubscriptionsUrl = "",
        ownerOrganizationsUrl = "",
        ownerReposUrl = "",
        ownerEventsUrl = "",
        ownerReceivedEventsUrl = "",
        ownerType = "",
        ownerSiteAdmin = false,
    ),
    isPrivate = false,
    htmlUrl = "",
    description = "",
    fork = false,
    url = "",
    archiveUrl = "",
    assigneesUrl = "",
    blobsUrl = "",
    branchesUrl = "",
    collaboratorsUrl = "",
    commentsUrl = "",
    commitsUrl = "",
    compareUrl = "",
    contentsUrl = "",
    contributorsUrl = "",
    deploymentsUrl = "",
    downloadsUrl = "",
    eventsUrl = "",
    forksUrl = "",
    gitCommitsUrl = "",
    gitRefsUrl = "",
    gitTagsUrl = "",
    gitUrl = "",
    issueCommentUrl = "",
    issueEventsUrl = "",
    issuesUrl = "",
    keysUrl = "",
    labelsUrl = "",
    languagesUrl = "",
    mergesUrl = "",
    milestonesUrl = "",
    notificationsUrl = "",
    pullsUrl = "",
    releasesUrl = "",
    sshUrl = "",
    stargazersUrl = "",
    statusesUrl = "",
    subscribersUrl = "",
    subscriptionUrl = "",
    tagsUrl = "",
    teamsUrl = "",
    treesUrl = "",
    cloneUrl = "",
    mirrorUrl = "",
    hooksUrl = "",
    svnUrl = "",
    homepage = "",
    language = "",
    forksCount = 0,
    stargazersCount = 0,
    watchersCount = 0,
    size = 0,
    defaultBranch = "",
    openIssuesCount = 0,
    isTemplate = false,
    topics = listOf(),
    hasIssues = false,
    hasProjects = false,
    hasWiki = false,
    hasPages = false,
    hasDownloads = false,
    archived = false,
    disabled = false,
    visibility = "",
    pushedAt = "2014-11-10T23:38:30".toLocalDateTime(),
    createdAt = "2014-11-10T23:38:30".toLocalDateTime(),
    updatedAt = "2014-11-10T23:38:30".toLocalDateTime(),
    permissions = mapOf(),
    allowRebaseMerge = false,
    templateRepository = "",
    tempCloneToken = "",
    allowSquashMerge = false,
    allowAutoMerge = false,
    deleteBranchOnMerge = false,
    allowMergeCommit = false,
    subscribersCount = 0,
    networkCount = 0,
    license = mapOf(),
    forks = 0,
    openIssues = 0,
    watchers = 0
)
