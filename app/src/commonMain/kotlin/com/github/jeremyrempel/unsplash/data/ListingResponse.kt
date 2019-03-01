package com.github.jeremyrempel.unsplash.data

import kotlinx.serialization.*

@Serializable
data class ListingResponse(val count: Int, val results: List<Result>) {
    @Serializable
    data class Result(val title: String, val description: String)
}