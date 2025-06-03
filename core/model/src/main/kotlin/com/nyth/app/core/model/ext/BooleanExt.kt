package com.nyth.app.core.model.ext

object BooleanExt {
    fun Boolean?.safeGet(default: Boolean = false) = this ?: default
}