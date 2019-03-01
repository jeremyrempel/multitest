package com.github.jeremyrempel.unsplash.api

import com.github.jeremyrempel.unsplash.data.ListingResponse

interface ListingApi {
    suspend fun getListing(id: String): ListingResponse
}