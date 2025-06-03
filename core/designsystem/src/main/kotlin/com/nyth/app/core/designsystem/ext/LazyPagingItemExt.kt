package com.nyth.app.core.designsystem.ext

import androidx.paging.compose.LazyPagingItems

object LazyPagingItemExt {
    /**
     * To get any index of list safely
     */
    fun <T : Any> LazyPagingItems<T>?.safeGet(index: Int): T? = try {
        if (this != null && itemCount > index) {
            get(index)
        } else {
            null
        }
    } catch (e: Exception) {
        null
    }
}