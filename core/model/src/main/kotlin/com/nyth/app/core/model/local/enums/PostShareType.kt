package com.nyth.app.core.model.local.enums

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class PostShareType(val type: String) : Parcelable {
    Public(type = "Public"),
    PublicCommunity(type = "PublicCommunity"),
    PrivateCommunity(type = "PrivateCommunity"),
    EventShare(type = "EventShare"),
    EventPost(type = "EventPost");
}