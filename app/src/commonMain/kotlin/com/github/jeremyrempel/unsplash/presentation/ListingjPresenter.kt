package com.github.jeremyrempel.unsplash.presentation

import com.github.jeremyrempel.unsplash.api.ListingApi
import com.github.jeremyrempel.unsplash.api.PhotoApi
import com.github.jeremyrempel.unsplash.di.kodein
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.erased.instance
import kotlin.coroutines.CoroutineContext

class ListingjPresenter(
    uiContext: CoroutineContext,
    val view: ListingView
) : CoroutinePresenter(uiContext, view), ListingActions {

    val api: ListingApi by kodein.instance("Api")

    override fun onRequestData() = updateData()

    private fun updateData() {
        view.isUpdating = true

        GlobalScope.launch(coroutineContext) {
            val response = api.getListing("")
            view.onUpdate(response)
        }.invokeOnCompletion {
            view.isUpdating = false
        }
    }
}