package com.github.jeremyrempel.unsplash.presentation

import com.github.jeremyrempel.unsplash.api.PhotoApi
import com.github.jeremyrempel.unsplash.di.kodein
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.erased.instance
import kotlin.coroutines.CoroutineContext

class PhotoPresenter(
    uiContext: CoroutineContext,
    val view: PhotoView
) : CoroutinePresenter(uiContext, view), PhotoActions {

    val api: PhotoApi by kodein.instance("Api")

    override fun onRequestData() = updateData()

    private fun updateData() {
        view.isUpdating = true

        GlobalScope.launch(coroutineContext) {
            val response = api.getRandom()
            view.onUpdate(response)
        }.invokeOnCompletion {
            view.isUpdating = false
        }
    }
}