package com.github.jeremyrempel.unsplash.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhotoResponse(val id: String, val description: String?, val exif: Exif?, val urls: Urls, val user: User) {

    @Serializable
    data class Exif(val make: String?, val model: String?)

    @Serializable
    data class Urls(val raw: String, val full: String, val regular: String, val thumb: String)

    @Serializable
    data class User(val username: String, @SerialName("first_name") val firstName: String, @SerialName("last_name") val lastName: String)
}
