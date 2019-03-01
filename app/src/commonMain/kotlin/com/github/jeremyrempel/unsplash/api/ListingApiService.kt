package com.github.jeremyrempel.unsplash.api

import com.github.jeremyrempel.unsplash.LogLevel
import com.github.jeremyrempel.unsplash.data.ListingResponse
import com.github.jeremyrempel.unsplash.log
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.http.takeFrom

class ListingApiService(private val client: HttpClient, private val endPoint: String, private val apiId: String) :
    ListingApi {

    companion object {
        internal val TAG = ListingApiService::class.toString()
    }

    override suspend fun getListing(id: String): ListingResponse = client.get {
        log(LogLevel.DEBUG, TAG, "Getting listing from API")
        apiUrl("v2/listings/active")
    }

    private fun HttpRequestBuilder.apiUrl(path: String) {
        url {
            takeFrom(endPoint)
            encodedPath = path
            parameters["api_key"] = apiId
        }
    }
}