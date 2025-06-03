package com.nyth.app.core.model.remote.request


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RequestPhoneCodeRequest(
    @Json(name = "phoneNumber")
    val phoneNumber: String? = null
)