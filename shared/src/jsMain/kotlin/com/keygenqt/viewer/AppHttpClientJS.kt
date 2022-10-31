package com.keygenqt.viewer


import com.keygenqt.viewer.data.responses.RocketModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.js.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.*
import kotlinx.serialization.json.Json
import kotlin.js.Promise

@OptIn(DelicateCoroutinesApi::class)
@Suppress("NON_EXPORTABLE_TYPE")
@JsExport
class AppHttpClientJS(
    private var token: String = ""
) {
    private val json = Json {
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true
    }

    fun rockets(): Promise<List<RocketModel>> {
        return GlobalScope.promise {
            val client = HttpClient(Js) {
                install(ContentNegotiation) {
                    json(json)
                }
            }
            client.get("https://api.spacexdata.com/v4/launches").body()
        }
    }
}