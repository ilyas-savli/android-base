package com.nyth.app.core.model.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MetadataFormResponse(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "metadataTypeId")
    val metadataTypeId: String? = null,
    @Json(name = "control_type")
    val controlType: String? = null,
    @Json(name = "form_type")
    val formType: String? = null,
    @Json(name = "order")
    val order: Int? = null,
    @Json(name = "label")
    val label: String? = null,
    @Json(name = "placeholder")
    val placeholder: String? = null,
    @Json(name = "multipleSelection")
    val multipleSelection: Boolean? = null,
    @Json(name = "required")
    val required: Boolean? = null,
    @Json(name = "filterable")
    val filterable: Boolean? = null,
    @Json(name = "mask")
    val mask: String? = null,
    @Json(name = "mask_type")
    val maskType: String? = null,
    @Json(name = "validationRules")
    val validationRules: List<ValidationRule?>? = null,
    @Json(name = "options")
    val options: List<Option>? = null
) {
    @JsonClass(generateAdapter = true)
    data class ValidationRule(
        @Json(name = "type")
        val type: String? = null,
        @Json(name = "value")
        val value: String? = null
    )

    @JsonClass(generateAdapter = true)
    data class Option(
        @Json(name = "label")
        val label: String? = null,
        @Json(name = "value")
        val value: String? = null
    )
}