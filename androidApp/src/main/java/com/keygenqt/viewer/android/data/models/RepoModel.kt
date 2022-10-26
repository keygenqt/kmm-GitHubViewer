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
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.keygenqt.viewer.android.interfaces.IModel

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
data class RepoModel(
    @PrimaryKey override val id: String,
    val url: String,
    val language: String?,
    val isPrivate: Boolean,
    val name: String,
    val fullName: String,
    val ownerName: String,
    val license: String?,
    val visibility: RepoVisibility,
    val desc: String?,
    val stargazersCount: Int,
    val forks: Int,
    val openIssuesCount: Int,
    val watchersCount: Int,
    val size: Int,
    val updatedAt: String,
    val createdAt: String,
) : IModel {
    companion object {
        fun mock() = RepoModel(
            id = "id",
            url = "https://keygenqt.com/",
            language = "kotlin",
            isPrivate = true,
            name = "name",
            fullName = "fullName",
            ownerName = "ownerName",
            license = "license",
            visibility = RepoVisibility.PRIVATE,
            desc = "desc",
            stargazersCount = 0,
            forks = 0,
            openIssuesCount = 0,
            watchersCount = 0,
            size = 0,
            updatedAt = "2016-02-15T10:21:09Z",
            createdAt = "2016-02-15T10:21:09Z",
        )
    }
}

fun com.keygenqt.viewer.data.responses.RepoModel.toModel() = RepoModel(
    id = this.id,
    url = this.url,
    language = this.language,
    isPrivate = this.isPrivate,
    name = this.name,
    fullName = this.fullName,
    ownerName = this.owner.login,
    license = this.license?.name,
    visibility = RepoVisibility.valueOf(this.visibility.uppercase()),
    desc = this.desc,
    stargazersCount = this.stargazersCount,
    forks = this.forks,
    openIssuesCount = this.openIssuesCount,
    watchersCount = this.watchersCount,
    size = this.size,
    updatedAt = this.updatedAt,
    createdAt = this.createdAt,
)
