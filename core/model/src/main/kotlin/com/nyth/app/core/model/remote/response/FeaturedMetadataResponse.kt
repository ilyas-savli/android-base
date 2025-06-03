package com.nyth.app.core.model.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FeaturedMetadataResponse(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "metadataType")
    val metadataType: String? = null,
    @Json(name = "metadataTypeId")
    val metadataTypeId: String? = null,
    @Json(name = "metadataValue")
    val metadataValue: String? = null,
    @Json(name = "metadataValueId")
    val metadataValueId: String? = null,
    @Json(name = "text")
    val text: String? = null
)