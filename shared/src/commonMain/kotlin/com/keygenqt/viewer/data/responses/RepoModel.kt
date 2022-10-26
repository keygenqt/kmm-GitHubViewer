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
 * Repo model
 */
@Serializable
data class RepoModel(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("full_name")
    val fullName: String,
    @SerialName("owner")
    val owner: RepoOwnerModel,
    @SerialName("private")
    val isPrivate: Boolean,
    @SerialName("html_url")
    val htmlUrl: String,
    @SerialName("description")
    val desc: String?,
    @SerialName("fork")
    val fork: Boolean,
    @SerialName("url")
    val url: String,
    @SerialName("archive_url")
    val archiveUrl: String,
    @SerialName("assignees_url")
    val assigneesUrl: String,
    @SerialName("blobs_url")
    val blobsUrl: String,
    @SerialName("branches_url")
    val branchesUrl: String,
    @SerialName("collaborators_url")
    val collaboratorsUrl: String,
    @SerialName("comments_url")
    val commentsUrl: String,
    @SerialName("commits_url")
    val commitsUrl: String,
    @SerialName("compare_url")
    val compareUrl: String,
    @SerialName("contents_url")
    val contentsUrl: String,
    @SerialName("contributors_url")
    val contributorsUrl: String,
    @SerialName("deployments_url")
    val deploymentsUrl: String,
    @SerialName("downloads_url")
    val downloadsUrl: String,
    @SerialName("events_url")
    val eventsUrl: String,
    @SerialName("forks_url")
    val forksUrl: String,
    @SerialName("git_commits_url")
    val gitCommitsUrl: String,
    @SerialName("git_refs_url")
    val gitRefsUrl: String,
    @SerialName("git_tags_url")
    val gitTagsUrl: String,
    @SerialName("git_url")
    val gitUrl: String,
    @SerialName("issue_comment_url")
    val issueCommentUrl: String,
    @SerialName("issue_events_url")
    val issueEventsUrl: String,
    @SerialName("issues_url")
    val issuesUrl: String,
    @SerialName("keys_url")
    val keysUrl: String,
    @SerialName("labels_url")
    val labelsUrl: String,
    @SerialName("languages_url")
    val languagesUrl: String,
    @SerialName("merges_url")
    val mergesUrl: String,
    @SerialName("milestones_url")
    val milestonesUrl: String,
    @SerialName("notifications_url")
    val notificationsUrl: String,
    @SerialName("pulls_url")
    val pullsUrl: String,
    @SerialName("releases_url")
    val releasesUrl: String,
    @SerialName("ssh_url")
    val sshUrl: String,
    @SerialName("stargazers_url")
    val stargazersUrl: String,
    @SerialName("statuses_url")
    val statusesUrl: String,
    @SerialName("subscribers_url")
    val subscribersUrl: String,
    @SerialName("subscription_url")
    val subscriptionUrl: String,
    @SerialName("tags_url")
    val tagsUrl: String,
    @SerialName("teams_url")
    val teamsUrl: String,
    @SerialName("trees_url")
    val treesUrl: String,
    @SerialName("clone_url")
    val cloneUrl: String,
    @SerialName("mirror_url")
    val mirrorUrl: String?,
    @SerialName("hooks_url")
    val hooksUrl: String,
    @SerialName("svn_url")
    val svnUrl: String,
    @SerialName("homepage")
    val homepage: String?,
    @SerialName("language")
    val language: String?,
    @SerialName("forks_count")
    val forksCount: Int,
    @SerialName("stargazers_count")
    val stargazersCount: Int,
    @SerialName("watchers_count")
    val watchersCount: Int,
    @SerialName("size")
    val size: Int,
    @SerialName("default_branch")
    val defaultBranch: String,
    @SerialName("open_issues_count")
    val openIssuesCount: Int,
    @SerialName("is_template")
    val isTemplate: Boolean,
    @SerialName("topics")
    val topics: List<String>,
    @SerialName("has_issues")
    val hasIssues: Boolean,
    @SerialName("has_projects")
    val hasProjects: Boolean,
    @SerialName("has_wiki")
    val hasWiki: Boolean,
    @SerialName("has_pages")
    val hasPages: Boolean,
    @SerialName("has_downloads")
    val hasDownloads: Boolean,
    @SerialName("archived")
    val archived: Boolean,
    @SerialName("disabled")
    val disabled: Boolean,
    @SerialName("visibility")
    val visibility: String,
    @SerialName("permissions")
    val permissions: RepoPermissionsModel,
    @SerialName("license")
    val license: RepoLicenseModel?,
    @SerialName("forks")
    val forks: Int,
    @SerialName("open_issues")
    val openIssues: Int,
    @SerialName("watchers")
    val watchers: Int,
    @SerialName("pushed_at")
    val pushedAt: String,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String,
)

/**
 * Repo license model
 */
@Serializable
data class RepoLicenseModel(
    @SerialName("key")
    val key: String,
    @SerialName("name")
    val name: String,
    @SerialName("spdx_id")
    val spdxId: String,
    @SerialName("url")
    val url: String?,
    @SerialName("node_id")
    val nodeId: String,
)

/**
 * Repo permissions model
 */
@Serializable
data class RepoPermissionsModel(
    @SerialName("admin")
    val admin: Boolean,
    @SerialName("maintain")
    val maintain: Boolean,
    @SerialName("push")
    val push: Boolean,
    @SerialName("triage")
    val triage: Boolean,
    @SerialName("pull")
    val pull: Boolean,
)

/**
 * Repo owner model
 */
@Serializable
data class RepoOwnerModel(
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

fun createRepoModel(
    id: String,
    url: String,
    language: String?,
    isPrivate: Boolean,
    name: String,
    fullName: String,
    ownerName: String,
    license: String?,
    visibility: String,
    desc: String?,
    stargazersCount: Int,
    forks: Int,
    openIssuesCount: Int,
    watchersCount: Int,
    size: Int,
    updatedAt: String,
    createdAt: String,
): RepoModel {
    return RepoModel(
        id = id,
        name = name,
        fullName = fullName,
        owner = RepoOwnerModel(
            id = "",
            login = ownerName,
            nodeId = "",
            avatarUrl = "",
            gravatarId = "",
            url = url,
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
        ),
        isPrivate = isPrivate,
        htmlUrl = "",
        desc = desc,
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
        mirrorUrl = null,
        hooksUrl = "",
        svnUrl = "",
        homepage = null,
        language = language,
        forksCount = 0,
        stargazersCount = stargazersCount,
        watchersCount = watchersCount,
        size = size,
        defaultBranch = "",
        openIssuesCount = openIssuesCount,
        isTemplate = false,
        topics = emptyList(),
        hasIssues = false,
        hasProjects = false,
        hasWiki = false,
        hasPages = false,
        hasDownloads = false,
        archived = false,
        disabled = false,
        visibility = visibility,
        permissions = RepoPermissionsModel(
            admin = false,
            maintain = false,
            push = false,
            triage = false,
            pull = false,
        ),
        license = RepoLicenseModel(
            key = "",
            name = license ?: "",
            spdxId = "",
            url = "",
            nodeId = "",
        ),
        forks = forks,
        openIssues = 0,
        watchers = 0,
        pushedAt = "",
        createdAt = createdAt,
        updatedAt = updatedAt,
    )
}
