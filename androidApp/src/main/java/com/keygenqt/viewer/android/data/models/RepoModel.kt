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
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.keygenqt.viewer.android.interfaces.IModel
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

/**
 * Enum for visibility model [RepoModel]
 */
enum class RepoVisibility {
    PUBLIC,
    PRIVATE,
    INTERNAL,
}

/**
 * Repo model
 */
@Entity
@Immutable
@Serializable
data class RepoModel(
    @PrimaryKey override val id: String,
    val name: String,
    val fullName: String,
    @Embedded val owner: RepoOwnerModel?,
    val isPrivate: Boolean,
    val htmlUrl: String,
    val description: String,
    val fork: Boolean,
    val url: String,
    val archiveUrl: String,
    val assigneesUrl: String,
    val blobsUrl: String,
    val branchesUrl: String,
    val collaboratorsUrl: String,
    val commentsUrl: String,
    val commitsUrl: String,
    val compareUrl: String,
    val contentsUrl: String,
    val contributorsUrl: String,
    val deploymentsUrl: String,
    val downloadsUrl: String,
    val eventsUrl: String,
    val forksUrl: String,
    val gitCommitsUrl: String,
    val gitRefsUrl: String,
    val gitTagsUrl: String,
    val gitUrl: String,
    val issueCommentUrl: String,
    val issueEventsUrl: String,
    val issuesUrl: String,
    val keysUrl: String,
    val labelsUrl: String,
    val languagesUrl: String,
    val mergesUrl: String,
    val milestonesUrl: String,
    val notificationsUrl: String,
    val pullsUrl: String,
    val releasesUrl: String,
    val sshUrl: String,
    val stargazersUrl: String,
    val statusesUrl: String,
    val subscribersUrl: String,
    val subscriptionUrl: String,
    val tagsUrl: String,
    val teamsUrl: String,
    val treesUrl: String,
    val cloneUrl: String,
    val mirrorUrl: String,
    val hooksUrl: String,
    val svnUrl: String,
    val homepage: String,
    val language: String,
    val forksCount: Int,
    val stargazersCount: Int,
    val watchersCount: Int,
    val size: Int,
    val defaultBranch: String,
    val openIssuesCount: Int,
    val isTemplate: Boolean,
    val topics: List<String>,
    val hasIssues: Boolean,
    val hasProjects: Boolean,
    val hasWiki: Boolean,
    val hasPages: Boolean,
    val hasDownloads: Boolean,
    val archived: Boolean,
    val disabled: Boolean,
    val visibility: RepoVisibility,
    val pushedAt: LocalDateTime?,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?,
    val permissions: Map<String, String?>,
    val allowRebaseMerge: Boolean,
    val templateRepository: String,
    val tempCloneToken: String,
    val allowSquashMerge: Boolean,
    val allowAutoMerge: Boolean,
    val deleteBranchOnMerge: Boolean,
    val allowMergeCommit: Boolean,
    val subscribersCount: Int,
    val networkCount: Int,
    val license: Map<String, String?>,
    val forks: Int,
    val openIssues: Int,
    val watchers: Int,
) : IModel
