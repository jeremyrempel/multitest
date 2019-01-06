package com.github.jeremyrempel.unsplash.api

interface PhotoApi {
    suspend fun getRandom(): PhotoResponse
}