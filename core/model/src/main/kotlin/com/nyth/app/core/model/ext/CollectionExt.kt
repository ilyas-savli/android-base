package com.nyth.app.core.model.ext

object CollectionExt {
    /**
     * To get any index of list safely
     */
    fun <T> List<T>?.safeGet(index: Int): T? = try {
        if (!isNullOrEmpty() && size > index) {
            get(index)
        } else {
            null
        }
    } catch (e: Exception) {
        null
    }
}
