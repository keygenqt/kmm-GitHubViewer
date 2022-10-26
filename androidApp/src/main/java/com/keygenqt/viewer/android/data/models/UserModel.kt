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
 * User model
 */
@Entity
@Immutable
data class UserModel(
    @PrimaryKey override val id: String,
    val avatarUrl: String,
    val name: String,
    val company: String?,
    val twitterUsername: String?,
    val publicRepos: Int,
    val followers: Int,
    val following: Int,
    val blog: String?,
    val location: String?,
    val bio: String?,
    val createdAt: String,
) : IModel {
    companion object {
        fun mock() = UserModel(
            id = "id",
            avatarUrl = "https://api.keygenqt.com/api/ps/file/d00b98c9-1e22-45ab-ab9f-396fe4cc7a52.jpg",
            name = "name",
            company = "company",
            twitterUsername = "twitterUsername",
            publicRepos = 0,
            followers = 0,
            following = 0,
            blog = "https://keygenqt.com/",
            location = "location",
            bio = "bio",
            createdAt = "2016-02-15T10:21:09Z",
        )
    }
}

fun com.keygenqt.viewer.data.responses.UserModel.toModel() = UserModel(
    id = this.id,
    avatarUrl = this.avatarUrl,
    name = this.name,
    company = this.company,
    twitterUsername = this.twitterUsername,
    publicRepos = this.publicRepos,
    followers = this.followers,
    following = this.following,
    blog = this.blog,
    location = this.location,
    bio = this.bio,
    createdAt = this.createdAt,
)
