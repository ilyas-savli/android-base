package com.nyth.app.core.model.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ComparisonSummaryResponse(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "motorcycles")
    val motorcycles: List<Motorcycle>? = null
) {
    @JsonClass(generateAdapter = true)
    data class Motorcycle(
        @Json(name = "motorcycleId")
        val motorcycleId: String? = null,
        @Json(name = "brandName")
        val brandName: String? = null,
        @Json(name = "brandImageUrl")
        val brandImageUrl: String? = null,
        @Json(name = "modelName")
        val modelName: String? = null,
        @Json(name = "brandModelId")
        val brandModelId: String? = null,
        @Json(name = "rate")
        val rate: Double? = null,
        @Json(name = "voteCount")
        val voteCount: Int? = null,
        @Json(name = "imageUrl")
        val imageUrl: String? = null
    )
}