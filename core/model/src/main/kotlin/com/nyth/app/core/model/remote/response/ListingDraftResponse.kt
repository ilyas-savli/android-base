package com.nyth.app.core.model.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListingDraftResponse(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "imageUrl")
    val imageUrl: String? = null,
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "price")
    val price: Price? = null,
    @Json(name = "city")
    val city: String? = null,
    @Json(name = "metadata")
    val metadata: List<String?>? = emptyList()
) {
    @JsonClass(generateAdapter = true)
    data class Price(
        @Json(name = "currency")
        val currency: String? = null,
        @Json(name = "amount")
        val amount: Int? = null
    )
}