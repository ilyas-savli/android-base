package com.nyth.app.core.model.remote.request

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class PostPaymentCreateUrlRequest (
    @Json(name = "fullName")
    val fullName: String? = null,
    @Json(name = "cardNumber")
    val cardNumber: String? = null,
    @Json(name = "expMonth")
    val expMonth: String? = null,
    @Json(name = "expYear")
    val expYear: String? = null,
    @Json(name = "cvc")
    val cvc: String? = null,
    @Json(name = "transactionType")
    val transactionType: TransactionType? = null,
    @Json(name = "returnUrl")
    val returnUrl: String? = null,
    @Json(name = "accountId")
    val accountId: String? = null,
    @Json(name = "listingId")
    val listingId: String? = null
) : Parcelable {
    @Parcelize
    enum class TransactionType(val value: String) : Parcelable {
        Listing("Listing");
    }
}