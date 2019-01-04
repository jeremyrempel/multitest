package com.github.jeremyrempel.unsplash.presentation

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.github.jeremyrempel.unsplash.api.PhotoApi
import kotlin.coroutines.CoroutineContext

class PhotoPresenter(
    uiContext: CoroutineContext,
    val view: PhotoView
) : CoroutinePresenter(uiContext, view), PhotoActions {

    fun onCreate() {

    }

    override fun onRequestData() = updateData()

    private fun updateData() {
        view.isUpdating = true

        GlobalScope.launch(coroutineContext) {
            val api = PhotoApi()

            val response = api.getRandom()
            view.onUpdate(response)
        }.invokeOnCompletion {
            view.isUpdating = false
        }
    }
}