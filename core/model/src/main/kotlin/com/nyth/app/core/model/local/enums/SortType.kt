package com.nyth.app.core.model.local.enums

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class SortType(val type: String) : Parcelable {
    Popular(type = "Popular"),
    Recent(type = "Recent");

    companion object {
        fun find(type: String?) = values().firstOrNull { it.type == type }
    }
}