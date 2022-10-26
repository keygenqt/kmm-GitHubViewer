package com.keygenqt.viewer.services.impl

import com.keygenqt.viewer.BuildKonfig
import com.keygenqt.viewer.data.requests.AuthRequest
import com.keygenqt.viewer.data.responses.SecurityModel
import com.keygenqt.viewer.utils.AppConstants
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class PostNetwork(val client: HttpClient) {

    /**
     * Get tokens GitHub rest api after oauth by code
     */
    @Throws(Exception::class)
    suspend fun oauth(
        code: String
    ): SecurityModel {
        return client.post(AppConstants.Links.AUTH_URL) {
            setBody(
                AuthRequest(
                    code = code,
                    client_secret = BuildKonfig.GITHUB_CLIENT_SECRET,
                    client_id = BuildKonfig.GITHUB_CLIENT_ID,
                )
            )
        }.body()
    }
}