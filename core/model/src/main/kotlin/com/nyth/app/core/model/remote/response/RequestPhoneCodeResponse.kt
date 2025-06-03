package com.nyth.app.core.model.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RequestPhoneCodeResponse(
    @Json(name = "code")
    val code: String? = null
)