package com.nyth.app.core.model.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RelatedLinksResponse(
    @Json(name = "models")
    val models: List<Model>? = null,
    @Json(name = "cities")
    val cities: List<City>? = null,
    @Json(name = "brands")
    val brands: List<Brand>? = null,
    @Json(name = "engineTypes")
    val engineTypes: List<EngineType>? = null,
    @Json(name = "motorcycleTypes")
    val motorcycleTypes: List<MotorcycleType>? = null
) {
    @JsonClass(generateAdapter = true)
    data class Model(
        @Json(name = "queryObject")
        val queryObject: QueryObject? = null,
        @Json(name = "query")
        val query: String? = null,
        @Json(name = "name")
        val name: String? = null
    ) {
        @JsonClass(generateAdapter = true)
        data class QueryObject(
            @Json(name = "categoryType")
            val categoryType: String? = null,
            @Json(name = "brandModelIds")
            val brandModelIds: List<String>? = null
        )
    }

    @JsonClass(generateAdapter = true)
    data class City(
        @Json(name = "queryObject")
        val queryObject: QueryObject? = null,
        @Json(name = "query")
        val query: String? = null,
        @Json(name = "name")
        val name: String? = null
    ) {
        @JsonClass(generateAdapter = true)
        data class QueryObject(
            @Json(name = "categoryType")
            val categoryType: String? = null,
            @Json(name = "cityIds")
            val cityIds: List<String>? = null
        )
    }

    @JsonClass(generateAdapter = true)
    data class Brand(
        @Json(name = "queryObject")
        val queryObject: QueryObject? = null,
        @Json(name = "query")
        val query: String? = null,
        @Json(name = "name")
        val name: String? = null
    ) {
        @JsonClass(generateAdapter = true)
        data class QueryObject(
            @Json(name = "categoryType")
            val categoryType: String? = null,
            @Json(name = "brandIds")
            val brandIds: MutableList<String>? = null
        )
    }

    @JsonClass(generateAdapter = true)
    data class EngineType(
        @Json(name = "queryObject")
        val queryObject: QueryObject? = null,
        @Json(name = "query")
        val query: String? = null,
        @Json(name = "name")
        val name: String? = null
    ) {
        @JsonClass(generateAdapter = true)
        data class QueryObject(
            @Json(name = "categoryType")
            val categoryType: String? = null,
            @Json(name = "metadataFilters")
            val metadataFilters: List<MetadataFilter?>? = null
        ) {
            @JsonClass(generateAdapter = true)
            data class MetadataFilter(
                @Json(name = "id")
                val id: String? = null,
                @Json(name = "values")
                val values: List<String?>? = null
            )
        }
    }

    @JsonClass(generateAdapter = true)
    data class MotorcycleType(
        @Json(name = "queryObject")
        val queryObject: QueryObject? = null,
        @Json(name = "query")
        val query: String? = null,
        @Json(name = "name")
        val name: String? = null
    ) {
        @JsonClass(generateAdapter = true)
        data class QueryObject(
            @Json(name = "categoryType")
            val categoryType: String? = null,
            @Json(name = "vehicleTypeIds")
            val vehicleTypeIds: List<String>? = null
        )
    }
}