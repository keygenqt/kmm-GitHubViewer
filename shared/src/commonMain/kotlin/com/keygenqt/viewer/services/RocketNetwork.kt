package com.keygenqt.viewer.services

import com.keygenqt.viewer.base.AppHttpClient.httpClient
import com.keygenqt.viewer.data.responses.RocketModel
import io.ktor.client.call.*
import io.ktor.client.request.*

class RocketNetwork {
    @Throws(Exception::class)
    suspend fun getRockets(): List<RocketModel> {
        return httpClient.get("https://api.spacexdata.com/v4/launches").body()
    }
}