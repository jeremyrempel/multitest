package com.github.jeremyrempel.unsplash

import android.util.Log

actual fun log(level: LogLevel, tag: String, message: String, error: Throwable) {
    when (level) {
        is LogLevel.DEBUG -> Log.d(tag, message, error)
        is LogLevel.INFO -> Log.i(tag, message, error)
        is LogLevel.WARN -> Log.w(tag, message, error)
        is LogLevel.ERROR -> Log.e(tag, message, error)
    }
}

actual fun log(level: LogLevel, tag: String, message: String) {
    when (level) {
        is LogLevel.DEBUG -> Log.d(tag, message)
        is LogLevel.INFO -> Log.i(tag, message)
        is LogLevel.WARN -> Log.w(tag, message)
        is LogLevel.ERROR -> Log.e(tag, message)
    }
}