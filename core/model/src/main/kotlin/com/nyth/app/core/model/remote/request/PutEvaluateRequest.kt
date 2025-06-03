package com.nyth.app.core.model.remote.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PutEvaluateRequest(
    @Json(name = "motorcycleId")
    val motorcycleId: String? = null,
)