package test.presentation

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import test.api.PhotoApi
import kotlin.coroutines.CoroutineContext

class PhotoPresenter(
    uiContext: CoroutineContext,
    val view: PhotoView
) : CoroutinePresenter(uiContext, view), PhotoActions {

    fun onCreate() {

    }

    override fun onRequestData() = updateData()

    private fun updateData() {
        GlobalScope.launch(coroutineContext) {
            val api = PhotoApi()
            view.isUpdating = true
            view.onUpdate(api.getRandom())
        }.invokeOnCompletion {
            view.isUpdating = false
        }
    }
}