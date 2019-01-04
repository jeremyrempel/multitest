package com.github.jeremyrempel.unsplash.presentation

import com.github.jeremyrempel.unsplash.LogLevel
import com.github.jeremyrempel.unsplash.api.PhotoApi
import com.github.jeremyrempel.unsplash.log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class CoroutinePresenter(
    private val mainContext: CoroutineContext, // TODO: Use Dispatchers.Main instead when it will be supported on iOS
    private val baseView: BaseView
) : CoroutineScope {

    companion object {
        internal val TAG = CoroutinePresenter::class.toString()
    }

    private val job = Job()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        log(LogLevel.ERROR, TAG, "Coroutine Error", throwable)
        baseView.showError(throwable)
    }

    override val coroutineContext: CoroutineContext
        get() = mainContext + job + exceptionHandler

    open fun onDestroy() {
        job.cancel()
    }
}