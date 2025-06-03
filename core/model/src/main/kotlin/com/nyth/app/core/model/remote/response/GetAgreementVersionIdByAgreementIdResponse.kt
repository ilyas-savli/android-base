package com.nyth.app.core.model.remote.response
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetAgreementVersionIdByAgreementIdResponse(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "isActive")
    val isActive: Boolean? = null,
    @Json(name = "versionNumber")
    val versionNumber: Int? = null
)
