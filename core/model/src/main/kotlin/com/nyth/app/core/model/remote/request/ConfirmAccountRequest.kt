package com.nyth.app.core.model.remote.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ConfirmAccountRequest(
    @Json(name = "email")
    val email: String?,
    @Json(name = "token")
    val token: String?
)