package com.nyth.app.core.model.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListingFiltersResponse(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "imageUrl")
    val imageUrl: String? = null,
    @Json(name = "query")
    val query: ListingFiltersQuery? = null
) {
    @JsonClass(generateAdapter = true)
    data class ListingFiltersQuery(
        @Json(name = "categoryType")
        val categoryType: String? = null,
        @Json(name = "brandIds")
        val brandIds: List<String?>? = emptyList(),
        @Json(name = "brandModelIds")
        val brandModelIds: List<String?>? = emptyList(),
        @Json(name = "vehicleTypeIds")
        val vehicleTypeIds: List<String>? = emptyList(),
        @Json(name = "maxPrice")
        val maxPrice: Int? = null
    )
}