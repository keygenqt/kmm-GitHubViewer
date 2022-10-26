package com.keygenqt.viewer.services.impl

import com.keygenqt.viewer.data.requests.RepoRequest
import com.keygenqt.viewer.data.requests.UserRequest
import com.keygenqt.viewer.data.responses.RepoModel
import com.keygenqt.viewer.data.responses.UserModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class PatchNetwork(val client: HttpClient) {

    /**
     * Update repo data by url
     */
    @Throws(Exception::class)
    suspend fun updateRepo(
        url: String,
        body: RepoRequest
    ): RepoModel {
        return client.patch(url) {
            setBody(body)
        }.body()
    }

    /**
     * Update user data
     */
    @Throws(Exception::class)
    suspend fun updateUser(
        body: UserRequest
    ): UserModel {
        return client.patch("user") {
            setBody(body)
        }.body()
    }
}