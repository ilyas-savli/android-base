package com.nyth.app.core.model.remote.request


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ConfirmPhoneNumberRequest(
    @Json(name = "phoneNumber")
    val phoneNumber: String? = null,
    @Json(name = "code")
    val code: String? = null
)