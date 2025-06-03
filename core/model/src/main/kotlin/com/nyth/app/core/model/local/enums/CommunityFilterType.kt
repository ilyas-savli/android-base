package com.nyth.app.core.model.local.enums

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class CommunityFilterType(val type: String, val tag : String) : Parcelable {
    All(type = "All", tag = "Tümü"),
    New(type = "New", tag = "Yeniler"),
    Popular(type = "Popular", tag = "Kalabalık Topluluk");
}

fun getCommunityFilterTypeByTag(tag: String): CommunityFilterType? {
    return CommunityFilterType.values().firstOrNull { it.tag == tag }
}