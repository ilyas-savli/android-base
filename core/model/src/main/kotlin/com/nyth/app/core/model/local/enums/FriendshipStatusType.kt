package com.nyth.app.core.model.local.enums

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class FriendshipStatusType(val type: String, val tag : String) : Parcelable {
    PENDING(type = "Pending", tag = "Bekliyor"),
    ACCEPTED(type = "Accepted", tag = "Kabul Edildi"),
    Declined(type = "Declined", tag = "Reddedildi"),
    NONE(type = "None", tag = "Hiçbiri");
}

fun getFriendshipStatusTypeByType(type: String): FriendshipStatusType? {
    return FriendshipStatusType.values().firstOrNull { it.type == type }
}