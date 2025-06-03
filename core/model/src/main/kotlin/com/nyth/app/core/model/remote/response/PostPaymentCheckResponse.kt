package com.nyth.app.core.model.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostPaymentCheckResponse(
    @Json(name = "paymentStatus")
    val paymentStatus: Boolean?,
    @Json(name = "description")
    val description: String?,
)