package com.nyth.app.core.model.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
@JsonClass(generateAdapter = true)
data class ListingShowCaseResponse(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "classifiedAdNumber")
    val classifiedAdNumber: Int? = null,
    @Json(name = "slug")
    val slug: String? = null,
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "imageUrl")
    val imageUrl: String? = null,
    @Json(name = "price")
    val price: Price? = null,
    @Json(name = "city")
    val city: City? = null,
    @Json(name = "metadata")
    val metadata: List<Metadata?>? = null,
    @Json(name = "acceleratePlaces")
    val acceleratePlaces: List<String?>? = null,
) {
    @JsonClass(generateAdapter = true)
    data class Price(
        @Json(name = "currency")
        val currency: String? = null,
        @Json(name = "amount")
        val amount: Double? = null,
    )

    @JsonClass(generateAdapter = true)
    data class City(
        @Json(name = "id")
        val id: String? = null,
        @Json(name = "name")
        val name: String? = null,
    )

    @JsonClass(generateAdapter = true)
    data class Metadata(
        @Json(name = "id")
        val id: String? = null,
        @Json(name = "metadataTypeId")
        val metadataTypeId: String? = null,
        @Json(name = "metadataType")
        val metadataType: String? = null,
        @Json(name = "metadataValueId")
        val metadataValueId: String? = null,
        @Json(name = "metadataValue")
        val metadataValue: String? = null,
    )
}