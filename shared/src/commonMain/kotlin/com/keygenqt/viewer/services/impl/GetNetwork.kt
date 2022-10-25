package com.keygenqt.viewer.services.impl

import com.keygenqt.viewer.data.responses.RepoModel
import com.keygenqt.viewer.data.responses.RocketModel
import com.keygenqt.viewer.utils.AppConstants
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class GetNetwork(val client: HttpClient) {

    @Throws(Exception::class)
    suspend fun rockets(): List<RocketModel> {
        return client.get("https://api.spacexdata.com/v4/launches").body()
    }

    @Throws(Exception::class)
    suspend fun repos(
        page: Int = 1,
        isSortASC: Boolean = false,
    ): List<RepoModel> {
        return client.get("user/repos") {
            url {
                with(parameters) {
                    append("page", page.toString())
                    append("per_page", AppConstants.PAGE_LIMIT.toString())
                    append("type", "owner")
                    append("sort", "created")
                    append("direction", if (isSortASC) "asc" else "desc")
                }
            }
        }.body()
    }
}