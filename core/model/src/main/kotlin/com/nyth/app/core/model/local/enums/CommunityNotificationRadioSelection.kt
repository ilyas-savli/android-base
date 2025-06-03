package com.nyth.app.core.model.local.enums

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class CommunityNotificationRadioSelection(val type: String) : Parcelable {
    ALL_POSTS(type = "All"),
    ONLY_FRIENDS(type = "OnlyFriends"),
    ALL_CLOSED(type = "None");

    fun isType(type: String): Boolean {
        return this.type == type
    }
}
fun getCommunityNotificationRadioSelectionByType(type: String): CommunityNotificationRadioSelection? {
    return CommunityNotificationRadioSelection.values().firstOrNull { it.type == type }
}