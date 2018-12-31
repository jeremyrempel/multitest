package test.api

import io.ktor.client.HttpClient
import io.ktor.client.features.ExpectSuccess
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.http.takeFrom
import kotlinx.serialization.json.JSON

class PhotoApi {

    private val endPoint = "https://api.unsplash.com"
    private val clientId = "7b62093f7803511c09584f0d978630a4ab3d5f1b3f5c70f97c30f6dbef25fdbf"

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
        apiUrl("photos", "random")
    }

    fun close() = client.close()

    private fun HttpRequestBuilder.apiUrl(vararg components: String) {
        url {
            takeFrom(endPoint)
            path(components.asList())
            parameters["client_id"] = clientId
        }
    }
}