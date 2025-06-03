package com.nyth.app.core.model.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PriceResponse(
    @Json(name = "amount")
    val amount: Int? = null,
    @Json(name = "currency")
    val currency: String? = null
)