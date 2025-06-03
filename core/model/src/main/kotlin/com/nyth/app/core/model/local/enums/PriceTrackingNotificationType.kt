package com.nyth.app.core.model.local.enums

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class PriceTrackingNotificationType(val type: String) : Parcelable {
    EveryPriceDrop(type = "EveryPriceDrop"),
    PriceDropDefinedPrice(type = "PriceDropDefinedPrice"),
    MuteNotifications(type = "MuteNotifications");

    companion object {
        fun find(type: String?) = values().firstOrNull { it.type == type }
    }
}