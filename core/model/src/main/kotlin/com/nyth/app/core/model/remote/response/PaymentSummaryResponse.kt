package com.nyth.app.core.model.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PaymentSummaryResponse(
    @Json(name = "listingId") val listingId: String? = null,
    @Json(name = "listingNumber") val listingNumber: Int? = null,
    @Json(name = "totalAmount") val totalAmount: Price,
    @Json(name = "items") val items: List<AcceleratePriceItemResponse>? = null
) {
    @JsonClass(generateAdapter = true)
    data class Price(
        @Json(name = "currency") val currency: String? = null,
        @Json(name = "amount") val amount: Double? = null
    )
    @JsonClass(generateAdapter = true)
    data class AcceleratePriceItemResponse(
        @Json(name = "listingAcceleratePackageId") val listingAcceleratePackageId: String? = null,
        @Json(name = "name") val name: String? = null,
        @Json(name = "price") val price: Price? = null
    )
}