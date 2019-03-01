package com.github.jeremyrempel.unsplash.di

import com.github.jeremyrempel.unsplash.api.*
import com.github.jeremyrempel.unsplash.data.ListingResponse
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
    bind<String>("ApiHost") with provider { "https://openapi.etsy.com" }
    bind<String>("ClientId") with provider { "zktxkxuersdp6mwi3m3d78e0" }
    bind<HttpClient>() with provider {
        HttpClient {

            install(JsonFeature) {
                serializer = KotlinxSerializer(JSON.nonstrict).apply {
                    setMapper(ListingResponse::class, ListingResponse.serializer())
                }

            }
            install(ExpectSuccess)
        }

    }
    bind<ListingApi>("Api") with provider {

        val client by kodein.instance<HttpClient>()
        val clientId by kodein.instance<String>("ClientId")
        val apiHost by kodein.instance<String>("ApiHost")

        ListingApiService(client, apiHost, clientId)
    }
}