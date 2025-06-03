package com.nyth.app.core.model.remote.request


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class ListingFilterRequest(
    @Json(name = "filter")
    val filter: Filter? = null,
    @Json(name = "pageIndex")
    var pageIndex: Int = 1,
    @Json(name = "pageSize")
    val pageSize: Int = 10,
) : Parcelable {
    @JsonClass(generateAdapter = true)
    @Parcelize
    data class Filter(
        @Json(name = "searchTerm")
        val searchTerm: String? = null,
        @Json(name = "categoryType")
        var categoryType: String? = null,
        @Json(name = "acceleratePlace")
        val acceleratePlace: String? = null,
        @Json(name = "categoryId")
        var categoryId: String? = null,
        @Json(name = "brandIds")
        val brandIds: MutableList<String>? = mutableListOf(),
        @Json(name = "brandModelIds")
        val brandModelIds: MutableList<String>? = mutableListOf(),
        @Json(name = "vehicleTypeIds")
        val vehicleTypeIds: MutableList<String>? = mutableListOf(),
        @Json(name = "cityIds")
        val cityIds: MutableList<String>? = mutableListOf(),
        @Json(name = "townIds")
        val townIds: MutableList<String>? = mutableListOf(),
        @Json(name = "minPrice")
        var minPrice: Int? = null,
        @Json(name = "maxPrice")
        var maxPrice: Int? = null,
        @Json(name = "metadataFilters")
        val metadataFilters: MutableList<MetadataFilter?>? = mutableListOf(),
        @Json(name = "hasImage")
        var hasImage: Boolean? = null,
        @Json(name = "hasVideo")
        var hasVideo: Boolean? = null,
        @Json(name = "hasEngineVideo")
        var hasEngineVideo: Boolean? = null,
        @Json(name = "orderBy")
        val orderBy: String? = null,
        @Json(name = "categories")
        val categories: List<Category>? = null
    ) : Parcelable {
        @JsonClass(generateAdapter = true)
        @Parcelize
        data class MetadataFilter(
            @Json(name = "metadataTypeId")
            val metadataTypeId: String? = null,
            @Json(name = "metadataValueIds")
            var metadataValueIds: MutableList<String>? = mutableListOf(),
            @Json(name = "numericMinValue")
            var numericMinValue: Int? = null,
            @Json(name = "numericMaxValue")
            var numericMaxValue: Int? = null
        ) : Parcelable

        @JsonClass(generateAdapter = true)
        @Parcelize
        data class Category(
            @Json(name = "id")
            val id: String? = null,
            @Json(name = "name")
            val name: String? = null,
            @Json(name = "categoryType")
            val categoryType: String? = null,
            @Json(name = "numericMaxValue")
            val childCategories: List<Category?>? = null,

            ) : Parcelable
    }
}