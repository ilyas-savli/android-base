package com.nyth.app.core.model.local

import android.os.Parcelable
import com.nyth.app.core.model.local.enums.ControlType
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class LocalMetadata(
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
    val validationRules: List<ValidationRule?>? = null,
    @Json(name = "maxNumericValue")
    val maxNumericValue: Int? = null,
    @Json(name = "minNumericValue")
    val minNumericValue: Int? = null,
    @Json(name = "key")
    val key: String? = null,
    @Json(name = "isCategories")
    val isCategories: Boolean? = false
) : Parcelable {
    @JsonClass(generateAdapter = true)
    @Parcelize
    data class Value(
        @Json(name = "id")
        val id: String? = null,
        @Json(name = "value")
        val value: String? = null,
        @Json(name = "count")
        val count: String? = null,
    ) : Parcelable

    @JsonClass(generateAdapter = true)
    @Parcelize
    data class ValidationRule(
        @Json(name = "type")
        val type: String? = null,
        @Json(name = "value")
        val value: String? = null
    ) : Parcelable
}