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
    val description: String?,
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
    val permissions: Map<String, String>,
    @SerialName("license")
    val license: Map<String, String?>?,
    @SerialName("forks")
    val forks: Int,
    @SerialName("open_issues")
    val openIssues: Int,
    @SerialName("watchers")
    val watchers: Int,
)

/**
 * Repo owner model
 */
@Serializable
data class RepoOwnerModel(
    @SerialName("id")
    val ownerId: String,
    @SerialName("login")
    val ownerLogin: String,
    @SerialName("node_id")
    val ownerNodeId: String,
    @SerialName("avatar_url")
    val ownerAvatarUrl: String,
    @SerialName("gravatar_id")
    val ownerGravatarId: String,
    @SerialName("url")
    val ownerUrl: String,
    @SerialName("html_url")
    val ownerHtmlUrl: String,
    @SerialName("followers_url")
    val ownerFollowersUrl: String,
    @SerialName("following_url")
    val ownerFollowingUrl: String,
    @SerialName("gists_url")
    val ownerGistsUrl: String,
    @SerialName("starred_url")
    val ownerStarredUrl: String,
    @SerialName("subscriptions_url")
    val ownerSubscriptionsUrl: String,
    @SerialName("organizations_url")
    val ownerOrganizationsUrl: String,
    @SerialName("repos_url")
    val ownerReposUrl: String,
    @SerialName("events_url")
    val ownerEventsUrl: String,
    @SerialName("received_events_url")
    val ownerReceivedEventsUrl: String,
    @SerialName("type")
    val ownerType: String,
    @SerialName("site_admin")
    val ownerSiteAdmin: Boolean,
)
