package com.nyth.app.core.designsystem.ext

import kotlinx.coroutines.delay

suspend fun <T> MutableList<T>.popUntil(item: T) {
    if (!contains(item)) return
    while (lastOrNull() != item) {
        removeLastOrNull() ?: break
        delay(100)
    }
}