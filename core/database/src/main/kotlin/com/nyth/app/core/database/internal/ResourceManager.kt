package com.nyth.app.core.database.internal

import android.content.Context

class ResourceManager (private val context: Context) {
    fun getString(resId: Int) = context.getString(resId)
    fun getString(resId: Int, vararg args: Any): String = context.getString(resId, *args)

}