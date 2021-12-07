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
package com.keygenqt.viewer.android.data.responses

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

/**
 * Response repo
 *
 * @property id repo identifier
 */
@Immutable
@Serializable
data class RepoResponse(
    val id: Int = 0,
    val name: String? = null,
    val full_name: String? = null,
    val owner: RepoOwnerResponse? = RepoOwnerResponse(),
    val private: Boolean? = null,
    val html_url: String? = null,
    val description: String? = null,
    val fork: Boolean? = null,
    val url: String? = null,
    val archive_url: String? = null,
    val assignees_url: String? = null,
    val blobs_url: String? = null,
    val branches_url: String? = null,
    val collaborators_url: String? = null,
    val comments_url: String? = null,
    val commits_url: String? = null,
    val compare_url: String? = null,
    val contents_url: String? = null,
    val contributors_url: String? = null,
    val deployments_url: String? = null,
    val downloads_url: String? = null,
    val events_url: String? = null,
    val forks_url: String? = null,
    val git_commits_url: String? = null,
    val git_refs_url: String? = null,
    val git_tags_url: String? = null,
    val git_url: String? = null,
    val issue_comment_url: String? = null,
    val issue_events_url: String? = null,
    val issues_url: String? = null,
    val keys_url: String? = null,
    val labels_url: String? = null,
    val languages_url: String? = null,
    val merges_url: String? = null,
    val milestones_url: String? = null,
    val notifications_url: String? = null,
    val pulls_url: String? = null,
    val releases_url: String? = null,
    val ssh_url: String? = null,
    val stargazers_url: String? = null,
    val statuses_url: String? = null,
    val subscribers_url: String? = null,
    val subscription_url: String? = null,
    val tags_url: String? = null,
    val teams_url: String? = null,
    val trees_url: String? = null,
    val clone_url: String? = null,
    val mirror_url: String? = null,
    val hooks_url: String? = null,
    val svn_url: String? = null,
    val homepage: String? = null,
    val language: String? = null,
    val forks_count: Int? = null,
    val stargazers_count: Int? = null,
    val watchers_count: Int? = null,
    val size: Int? = null,
    val default_branch: String? = null,
    val open_issues_count: Int? = null,
    val is_template: Boolean? = null,
    val topics: List<String>? = listOf(),
    val has_issues: Boolean? = null,
    val has_projects: Boolean? = null,
    val has_wiki: Boolean? = null,
    val has_pages: Boolean? = null,
    val has_downloads: Boolean? = null,
    val archived: Boolean? = null,
    val disabled: Boolean? = null,
    val visibility: String? = null,
    val pushed_at: String? = null,
    val created_at: String? = null,
    val updated_at: String? = null,
    val permissions: Map<String, String?>? = mapOf(),
    val allow_rebase_merge: Boolean? = null,
    val template_repository: String? = null,
    val temp_clone_token: String? = null,
    val allow_squash_merge: Boolean? = null,
    val allow_auto_merge: Boolean? = null,
    val delete_branch_on_merge: Boolean? = null,
    val allow_merge_commit: Boolean? = null,
    val subscribers_count: Int? = null,
    val network_count: Int? = null,
    val license: Map<String, String?>? = mapOf(),
    val forks: Int? = null,
    val open_issues: Int? = null,
    val watchers: Int? = null,
)

/**
 * Response repo owner
 *
 * @property id owner identifier
 */
@Immutable
@Serializable
data class RepoOwnerResponse(
    val id: Int = 0,
    val login: String? = null,
    val node_id: String? = null,
    val avatar_url: String? = null,
    val gravatar_id: String? = null,
    val url: String? = null,
    val html_url: String? = null,
    val followers_url: String? = null,
    val following_url: String? = null,
    val gists_url: String? = null,
    val starred_url: String? = null,
    val subscriptions_url: String? = null,
    val organizations_url: String? = null,
    val repos_url: String? = null,
    val events_url: String? = null,
    val received_events_url: String? = null,
    val type: String? = null,
    val site_admin: Boolean? = null,
)