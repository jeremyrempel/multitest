package com.github.jeremyrempel.unsplash.api

import com.github.jeremyrempel.unsplash.LogLevel
import com.github.jeremyrempel.unsplash.log
import io.ktor.client.HttpClient
import io.ktor.client.features.ExpectSuccess
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.http.takeFrom
import kotlinx.serialization.json.JSON

class PhotoApi {

    companion object {
        internal val TAG = PhotoApi::class.toString()
        internal const val endPoint = "https://api.unsplash.com"
        internal const val clientId = "7b62093f7803511c09584f0d978630a4ab3d5f1b3f5c70f97c30f6dbef25fdbf"
    }

    private val client by lazy {
        HttpClient {

            install(JsonFeature) {
                serializer = KotlinxSerializer(JSON.nonstrict).apply {
                    setMapper(PhotoResponse::class, PhotoResponse.serializer())
                }

            }
            install(ExpectSuccess)
        }
    }

    suspend fun getRandom(): PhotoResponse = client.get {
        log(LogLevel.DEBUG, TAG, "Getting random photo from API")
        apiUrl("photos/random")
    }

    fun close() = client.close()

    private fun HttpRequestBuilder.apiUrl(path: String) {
        url {
            takeFrom(endPoint)
            encodedPath = path
            parameters["client_id"] = clientId
        }
    }
}