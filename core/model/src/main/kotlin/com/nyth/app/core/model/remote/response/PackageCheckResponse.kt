package com.nyth.app.core.model.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class PackageCheckResponse(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "type")
    val type: String? = null,
    @Json(name = "featureType")
    val featureType: String? = null,
    @Json(name = "availableQuota")
    val availableQuota: Int? = null,
    @Json(name = "totalQuota")
    val totalQuota: Int? = null,
)