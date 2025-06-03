package com.nyth.app.core.model.remote.response

import android.os.Parcelable
import com.nyth.app.core.model.local.enums.ControlType
import com.nyth.app.core.model.remote.request.ListingFilterRequest
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

/**
 * Base Response
 */
@JsonClass(generateAdapter = true)
data class BaseResponse<T>(
    @Json(name = "filter")
    val filter: Filter?,
    @Json(name = "pageIndex")
    val pageIndex: Int? = null,
    @Json(name = "pageSize")
    val pageSize: Int? = null,
    @Json(name = "totalPages")
    val totalPages: Int? = null,
    @Json(name = "totalCount")
    val totalCount: Int? = null,
    @Json(name = "hasPreviousPage")
    val hasPreviousPage: Boolean? = null,
    @Json(name = "hasNextPage")
    val hasNextPage: Boolean? = null,
    @Json(name = "success")
    val success: Boolean?,
    @Json(name = "errors")
    val errors: List<String?>?,
    @Json(name = "data")
    val `data`: T?
) {
    @JsonClass(generateAdapter = true)
    data class Filter(
        @Json(name = "categories")
        val categories: List<ListingFilterRequest.Filter.Category?>? = null,
        @Json(name = "brands")
        val brands: List<Brand?>? = null,
        @Json(name = "cities")
        val cities: List<City?>? = null,
        @Json(name = "vehicleTypes")
        val vehicleTypes: List<VehicleType?>? = null,
        @Json(name = "price")
        val price: Price? = null,
        @Json(name = "metadata")
        val metadata: List<Metadata?>? = null
    ) {
        @JsonClass(generateAdapter = true)
        @Parcelize
        data class Brand(
            @Json(name = "id")
            val id: String? = null,
            @Json(name = "value")
            val value: String? = null,
            @Json(name = "dataCount")
            val dataCount: Int? = null,
            @Json(name = "subValues")
            val subValues: List<SubValue?>? = null
        ):Parcelable {
            @JsonClass(generateAdapter = true)
            @Parcelize
            data class SubValue(
                @Json(name = "id")
                val id: String? = null,
                @Json(name = "value")
                val value: String? = null,
                @Json(name = "dataCount")
                val dataCount: Int? = null
            ):Parcelable
        }

        @JsonClass(generateAdapter = true)
        @Parcelize
        data class City(
            @Json(name = "id")
            val id: String? = null,
            @Json(name = "value")
            val value: String? = null,
            @Json(name = "dataCount")
            val dataCount: Int? = null,
            @Json(name = "subValues")
            val subValues: List<SubValue?>? = null
        ) :Parcelable{
            @JsonClass(generateAdapter = true)
            @Parcelize
            data class SubValue(
                @Json(name = "id")
                val id: String? = null,
                @Json(name = "value")
                val value: String? = null,
                @Json(name = "dataCount")
                val dataCount: Int? = null
            ): Parcelable
        }

        @JsonClass(generateAdapter = true)
        data class VehicleType(
            @Json(name = "id")
            val id: String? = null,
            @Json(name = "value")
            val value: String? = null,
            @Json(name = "dataCount")
            val dataCount: Int? = null
        )

        @JsonClass(generateAdapter = true)
        data class Price(
            @Json(name = "minPrice")
            val minPrice: MinPrice? = null,
            @Json(name = "maxPrice")
            val maxPrice: MaxPrice? = null
        ) {
            @JsonClass(generateAdapter = true)
            data class MinPrice(
                @Json(name = "currency")
                val currency: String? = null,
                @Json(name = "amount")
                val amount: Int? = null
            )

            @JsonClass(generateAdapter = true)
            data class MaxPrice(
                @Json(name = "currency")
                val currency: String? = null,
                @Json(name = "amount")
                val amount: Int? = null
            )
        }

        @JsonClass(generateAdapter = true)
        @Parcelize
        data class Metadata(
            @Json(name = "id")
            val id: String? = null,
            @Json(name = "typeId")
            val typeId: String? = null,
            @Json(name = "order")
            val order: Int? = null,
            @Json(name = "name")
            val name: String? = null,
            @Json(name = "label")
            val label: String? = null,
            @Json(name = "placeholder")
            val placeholder: String? = null,
            @Json(name = "controlType")
            val controlType: ControlType? = null,
            @Json(name = "multipleSelection")
            var multipleSelection: Boolean? = null,
            @Json(name = "values")
            val values: List<Value?>? = null,
            @Json(name = "validationRules")
            val validationRules: List<ValidationRule?>? = null
        ) :Parcelable{
            @JsonClass(generateAdapter = true)
            @Parcelize
            data class Value(
                @Json(name = "id")
                val id: String? = null,
                @Json(name = "value")
                val value: String? = null
            ): Parcelable

            @JsonClass(generateAdapter = true)
            @Parcelize
            data class ValidationRule(
                @Json(name = "type")
                val type: String? = null,
                @Json(name = "value")
                val value: String? = null
            ): Parcelable
        }
    }
}