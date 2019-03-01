package com.github.jeremyrempel.unsplash.presentation

import com.github.jeremyrempel.unsplash.api.PhotoResponse
import com.github.jeremyrempel.unsplash.data.ListingResponse

interface ListingView : BaseView {
    var isUpdating: Boolean
    fun onUpdate(data: ListingResponse)
}

interface ListingActions {
    fun onRequestData()
}