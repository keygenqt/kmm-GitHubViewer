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
package com.keygenqt.viewer.android.data.mappers

import com.keygenqt.viewer.android.data.models.RepoModel
import com.keygenqt.viewer.android.data.models.RepoOwnerModel
import com.keygenqt.viewer.android.data.responses.RepoResponse
import com.keygenqt.viewer.android.extensions.toRepoVisibility
import kotlinx.datetime.toLocalDateTime
import timber.log.Timber

/**
 * Extension for response [RepoResponse]
 */
fun RepoResponse.toModel(): RepoModel {
    return RepoModel(
        id = id.toString(),
        name = name ?: "",
        fullName = full_name ?: "",
        owner = owner?.let {
            RepoOwnerModel(
                ownerId = it.id.toString(),
                ownerLogin = it.login ?: "",
                ownerNodeId = it.node_id ?: "",
                ownerAvatarUrl = it.avatar_url ?: "",
                ownerGravatarId = it.gravatar_id ?: "",
                ownerUrl = it.url ?: "",
                ownerHtmlUrl = it.html_url ?: "",
                ownerFollowersUrl = it.followers_url ?: "",
                ownerFollowingUrl = it.following_url ?: "",
                ownerGistsUrl = it.gists_url ?: "",
                ownerStarredUrl = it.starred_url ?: "",
                ownerSubscriptionsUrl = it.subscriptions_url ?: "",
                ownerOrganizationsUrl = it.organizations_url ?: "",
                ownerReposUrl = it.repos_url ?: "",
                ownerEventsUrl = it.events_url ?: "",
                ownerReceivedEventsUrl = it.received_events_url ?: "",
                ownerType = it.type ?: "",
                ownerSiteAdmin = it.site_admin ?: false,
            )
        },
        isPrivate = private ?: false,
        htmlUrl = html_url ?: "",
        description = description ?: "",
        fork = fork ?: false,
        url = url ?: "",
        archiveUrl = archive_url ?: "",
        assigneesUrl = assignees_url ?: "",
        blobsUrl = blobs_url ?: "",
        branchesUrl = branches_url ?: "",
        collaboratorsUrl = collaborators_url ?: "",
        commentsUrl = comments_url ?: "",
        commitsUrl = commits_url ?: "",
        compareUrl = compare_url ?: "",
        contentsUrl = contents_url ?: "",
        contributorsUrl = contributors_url ?: "",
        deploymentsUrl = deployments_url ?: "",
        downloadsUrl = downloads_url ?: "",
        eventsUrl = events_url ?: "",
        forksUrl = forks_url ?: "",
        gitCommitsUrl = git_commits_url ?: "",
        gitRefsUrl = git_refs_url ?: "",
        gitTagsUrl = git_tags_url ?: "",
        gitUrl = git_url ?: "",
        issueCommentUrl = issue_comment_url ?: "",
        issueEventsUrl = issue_events_url ?: "",
        issuesUrl = issues_url ?: "",
        keysUrl = keys_url ?: "",
        labelsUrl = labels_url ?: "",
        languagesUrl = languages_url ?: "",
        mergesUrl = merges_url ?: "",
        milestonesUrl = milestones_url ?: "",
        notificationsUrl = notifications_url ?: "",
        pullsUrl = pulls_url ?: "",
        releasesUrl = releases_url ?: "",
        sshUrl = ssh_url ?: "",
        stargazersUrl = stargazers_url ?: "",
        statusesUrl = statuses_url ?: "",
        subscribersUrl = subscribers_url ?: "",
        subscriptionUrl = subscription_url ?: "",
        tagsUrl = tags_url ?: "",
        teamsUrl = teams_url ?: "",
        treesUrl = trees_url ?: "",
        cloneUrl = clone_url ?: "",
        mirrorUrl = mirror_url ?: "",
        hooksUrl = hooks_url ?: "",
        svnUrl = svn_url ?: "",
        homepage = homepage ?: "",
        language = language ?: "",
        forksCount = forks_count ?: 0,
        stargazersCount = stargazers_count ?: 0,
        watchersCount = watchers_count ?: 0,
        size = size ?: 0,
        defaultBranch = default_branch ?: "",
        openIssuesCount = open_issues_count ?: 0,
        isTemplate = is_template ?: false,
        topics = topics ?: listOf(),
        hasIssues = has_issues ?: false,
        hasProjects = has_projects ?: false,
        hasWiki = has_wiki ?: false,
        hasPages = has_pages ?: false,
        hasDownloads = has_downloads ?: false,
        archived = archived ?: false,
        disabled = disabled ?: false,
        visibility = visibility.toRepoVisibility(),
        pushedAt = pushed_at?.replace("Z", "")?.toLocalDateTime(),
        createdAt = created_at?.replace("Z", "")?.toLocalDateTime(),
        updatedAt = updated_at?.replace("Z", "")?.toLocalDateTime(),
        permissions = permissions ?: mapOf(),
        allowRebaseMerge = allow_rebase_merge ?: false,
        templateRepository = template_repository ?: "",
        tempCloneToken = temp_clone_token ?: "",
        allowSquashMerge = allow_squash_merge ?: false,
        allowAutoMerge = allow_auto_merge ?: false,
        deleteBranchOnMerge = delete_branch_on_merge ?: false,
        allowMergeCommit = allow_merge_commit ?: false,
        subscribersCount = subscribers_count ?: 0,
        networkCount = network_count ?: 0,
        license = license ?: mapOf(),
        forks = forks ?: 0,
        openIssues = open_issues ?: 0,
        watchers = watchers ?: 0
    )
}

/**
 * Extension for list response [RepoResponse]
 */
fun List<RepoResponse>.toModels(): List<RepoModel> {
    return map { it.toModel() }
}
