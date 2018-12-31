package test.api

import kotlinx.serialization.Serializable

@Serializable
data class PhotoResponse(val id: String, val description: String)