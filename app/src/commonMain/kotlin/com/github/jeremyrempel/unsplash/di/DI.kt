package com.github.jeremyrempel.unsplash.di

import com.github.jeremyrempel.unsplash.api.PhotoApi
import com.github.jeremyrempel.unsplash.api.PhotoApiService
import com.github.jeremyrempel.unsplash.api.PhotoResponse
import io.ktor.client.HttpClient
import io.ktor.client.features.ExpectSuccess
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.json.JSON
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.provider

val kodein = Kodein {
    bind<String>("ApiHost") with provider { "https://api.unsplash.com" }
    bind<String>("ClientId") with provider { "7b62093f7803511c09584f0d978630a4ab3d5f1b3f5c70f97c30f6dbef25fdbf" }
    bind<HttpClient>() with provider {
        HttpClient {

            install(JsonFeature) {
                serializer = KotlinxSerializer(JSON.nonstrict).apply {
                    setMapper(PhotoResponse::class, PhotoResponse.serializer())
                }

            }
            install(ExpectSuccess)
        }

    }
    bind<PhotoApi>("Api") with provider {

        val client by kodein.instance<HttpClient>()
        val clientId by kodein.instance<String>("ClientId")
        val apiHost by kodein.instance<String>("ApiHost")

        PhotoApiService(client, apiHost, clientId)
    }
}