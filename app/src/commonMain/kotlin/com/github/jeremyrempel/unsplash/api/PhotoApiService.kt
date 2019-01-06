package com.github.jeremyrempel.unsplash.api

import com.github.jeremyrempel.unsplash.LogLevel
import com.github.jeremyrempel.unsplash.log
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.http.takeFrom

class PhotoApiService(private val client: HttpClient, private val endPoint: String, private val clientId: String) :
    PhotoApi {

    companion object {
        internal val TAG = PhotoApiService::class.toString()
    }

    override suspend fun getRandom(): PhotoResponse = client.get {
        log(LogLevel.DEBUG, TAG, "Getting random photo from API")
        apiUrl("photos/random")
    }

    private fun HttpRequestBuilder.apiUrl(path: String) {
        url {
            takeFrom(endPoint)
            encodedPath = path
            parameters["client_id"] = clientId
        }
    }
}