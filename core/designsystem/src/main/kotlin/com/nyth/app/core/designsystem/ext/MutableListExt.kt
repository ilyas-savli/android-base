package com.nyth.app.core.designsystem.ext

fun <T> MutableList<T>.popUntil(item: T) {
    if (!contains(item)) return
    while (lastOrNull() != item) {
        removeLastOrNull() ?: break
    }
}