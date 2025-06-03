package com.nyth.app.core.model.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FilterHeaderResponse(
    @Json(name = "filterName")
    val filterName: String? = null,
    @Json(name = "resId")
    val resId: Int? = null,
    @Json(name = "courseTypes")
    val courseTypes: List<String>,
    @Json(name = "isFree")
    val isFree: Boolean? = null,
    @Json(name = "isCertificated")
    val isCertificated: Boolean? = null,
)
