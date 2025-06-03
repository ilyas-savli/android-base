package com.nyth.app.core.model.local.enums

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class ListingNotificationType(val type: String) : Parcelable {
    ClassifiedAdPriceChanged(type = "ClassifiedAdPriceChanged"),
    ClassifiedAdInReview(type = "ClassifiedAdInReview"),
    ClassifiedAdApproved(type = "ClassifiedAdApproved"),
    ClassifiedAdRemoved(type = "ClassifiedAdRemoved"),
    ClassifiedAdRejected(type = "ClassifiedAdRejected"),
    ClassifiedAdPublicationExpiring(type = "ClassifiedAdPublicationExpiring"),
    ClassifiedAdViewCount(type = "ClassifiedAdViewCount"),
    ClassifiedAdFavoriteCount(type = "ClassifiedAdFavoriteCount"),
    ClassifiedAdNewMessage(type = "ClassifiedAdNewMessage"),
    AccelerateClassifiedAd(type = "AccelerateClassifiedAd"),
    ClassifiedAdUpdated(type = "ClassifiedAdUpdated"),
    ClassifiedAdCreated(type = "ClassifiedAdCreated");

    companion object {
        fun find(type: String?) = values().firstOrNull { it.type == type }
    }
}