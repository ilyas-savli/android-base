package com.nyth.app.core.model.remote.request

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class PostPaymentCheckRequest (
    @Json(name = "transactionType")
    val transactionType: TransactionType? = null,
    @Json(name = "listingId")
    val listingId: String? = null
) : Parcelable {
    @Parcelize
    enum class TransactionType(val value: String) : Parcelable {
        Listing("Listing");
    }
}