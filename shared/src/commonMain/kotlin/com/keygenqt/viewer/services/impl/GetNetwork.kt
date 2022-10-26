package com.keygenqt.viewer.services.impl

import com.keygenqt.viewer.data.responses.FollowerModel
import com.keygenqt.viewer.data.responses.RepoModel
import com.keygenqt.viewer.data.responses.RocketModel
import com.keygenqt.viewer.data.responses.UserModel
import com.keygenqt.viewer.utils.AppConstants
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class GetNetwork(val client: HttpClient) {

    /**
     * Test query without tokens from demo KMM
     */
    @Throws(Exception::class)
    suspend fun rockets(): List<RocketModel> {
        return client.get("https://api.spacexdata.com/v4/launches").body()
    }

    /**
     * Get list repos user
     */
    @Throws(Exception::class)
    suspend fun repos(
        page: Int = 1,
        isSortASC: Boolean = false,
    ): List<RepoModel> {
        return client.get("user/repos") {
            url {
                with(parameters) {
                    append("page", page.toString())
                    append("per_page", AppConstants.App.PAGE_LIMIT.toString())
                    append("type", "owner")
                    append("sort", "created")
                    append("direction", if (isSortASC) "asc" else "desc")
                }
            }
        }.body()
    }

    /**
     * Get repo data by url
     */
    @Throws(Exception::class)
    suspend fun repo(
        url: String
    ): RepoModel {
        return client.get(url).body()
    }

    /**
     * Get list user followers
     */
    @Throws(Exception::class)
    suspend fun followers(
        page: Int = 1
    ): List<FollowerModel> {
        return client.get("user/followers") {
            url {
                with(parameters) {
                    append("page", page.toString())
                    append("per_page", AppConstants.App.PAGE_LIMIT.toString())
                }
            }
        }.body()
    }

    /**
     * Get user
     */
    @Throws(Exception::class)
    suspend fun user(): UserModel {
        return client.get("user").body()
    }
}