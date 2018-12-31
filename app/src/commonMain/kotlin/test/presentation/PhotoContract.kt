package test.presentation

import test.api.PhotoResponse

interface PhotoView : BaseView {
    var isUpdating: Boolean
    fun onUpdate(data: PhotoResponse)
}

interface PhotoActions {
    fun onRequestData()
}