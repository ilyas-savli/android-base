package com.nyth.app.core.model.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetAgreementContentResponse(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "content")
    val content: String? = null,
    @Json(name = "agreementType")
    val agreementType: String? = null,
    @Json(name = "agreementId")
    val agreementId: String? = null,
    @Json(name = "versionNumber")
    val versionNumber: Int? = null
)