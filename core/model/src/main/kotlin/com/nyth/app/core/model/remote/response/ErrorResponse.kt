package com.nyth.app.core.model.remote.response

import com.nyth.app.core.model.local.enums.NetworkErrorType
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable

/**
 * Base Error Response
 */
@Serializable
@JsonClass(generateAdapter = true)
data class ErrorResponse(
    @Json(name = "errors")
    val errors: Errors? = null,
    @Json(name = "type")
    val type: String? = null,
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "status")
    val status: Int? = null,
    @Json(name = "traceId")
    val traceId: String? = null,
    var errorType: NetworkErrorType? = null,
    @Json(name = "success")
    val success: Boolean? = null,
    @Json(name = "errorList")
    val errorList: List<String?>? = null
) {
    @Serializable
    @JsonClass(generateAdapter = true)
    data class Errors(
        @Json(name = "filter.categoryType")
        val filterCategoryType: List<String?>? = null
    )
}

@Serializable
@JsonClass(generateAdapter = true)
data class ErrorStringResponse(
    @Json(name = "success")
    val success: Boolean? = null,
    @Json(name = "errors")
    val errors: List<String?>? = null,
    var errorType: NetworkErrorType? = null
)