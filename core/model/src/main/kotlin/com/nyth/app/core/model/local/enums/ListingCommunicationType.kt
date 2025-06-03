package com.nyth.app.core.model.local.enums

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class ListingCommunicationType(val type: String) : Parcelable {
    PhoneAndMessage(type = "PhoneAndMessage"),
    Phone(type = "Phone"),
    Message(type = "Message");

    companion object {
        fun find(type: String?) = values().firstOrNull { it.type == type }
    }
}