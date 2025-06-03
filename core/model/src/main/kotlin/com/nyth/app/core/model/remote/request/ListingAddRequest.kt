package com.nyth.app.core.model.remote.request

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class ListingAddRequest(
    @Json(name = "brandId")
    val brandId: String? = null,
    @Json(name = "modelId")
    val modelId: String? = null,
    @Json(name = "categoryId")
    val categoryId: String? = null,
    @Json(name = "typeId")
    val typeId: String? = null,
    @Json(name = "cityId")
    val cityId: String? = null,
    @Json(name = "townId")
    val townId: String? = null,
    @Json(name = "details")
    val details: List<Detail>? = emptyList(),
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "price")
    val price: Price? = null,
    @Json(name = "communicationPreference")
    val communicationPreference: String? = null
) : Parcelable {
    @JsonClass(generateAdapter = true)
    @Parcelize
    data class Detail(
        @Json(name = "typeId")
        val typeId: String? = null,
        @Json(name = "value")
        val value: String? = null
    ) : Parcelable

    @JsonClass(generateAdapter = true)
    @Parcelize
    data class Price(
        @Json(name = "currency")
        val currency: String? = null,
        @Json(name = "amount")
        val amount: Int? = null
    ) : Parcelable
}