package com.nyth.app.core.model.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AdvertDetailResponse(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "accountId")
    val accountId: String? = null,
    @Json(name = "classifiedAdNumber")
    val classifiedAdNumber: Int? = null,
    @Json(name = "slug")
    val slug: String? = null,
    @Json(name = "shareUrl")
    val shareUrl: String? = null,
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "price")
    val price: Price? = null,
    @Json(name = "favoriteDate")
    val favoriteDate: String? = null,
    @Json(name = "favoriteDatePrice")
    val favoriteDatePrice: Price? = null,
    @Json(name = "approvedAt")
    val approvedAt: String? = null,
    @Json(name = "communicationPreference")
    val communicationPreference: String? = null,
    @Json(name = "status")
    val status: String? = null,
    @Json(name = "priceChange")
    val priceChange: List<PriceChange?>? = null,
    @Json(name = "category")
    val category: Category? = null,
    @Json(name = "city")
    val city: City? = null,
    @Json(name = "town")
    val town: Town? = null,
    @Json(name = "brand")
    val brand: Brand? = null,
    @Json(name = "model")
    val model: Model? = null,
    @Json(name = "media")
    val media: List<Media?>? = null,
    @Json(name = "metadata")
    val metadata: List<Metadata?>? = null,
    @Json(name = "additionalMetadata")
    val additionalMetadata: List<AdditionalMetadata?>? = null,
    @Json(name = "featuredMetadata")
    val featuredMetadata: List<FeaturedMetadata?>? = null
) {
    @JsonClass(generateAdapter = true)
    data class Price(
        @Json(name = "currency")
        val currency: String? = null,
        @Json(name = "amount")
        val amount: Int? = null
    )

    @JsonClass(generateAdapter = true)
    data class Category(
        @Json(name = "id")
        val id: String? = null,
        @Json(name = "name")
        val name: String? = null,
        @Json(name = "categoryType")
        val categoryType: String? = null,
    )

    @JsonClass(generateAdapter = true)
    data class PriceChange(
        @Json(name = "date")
        val date: String? = null,
        @Json(name = "price")
        val price: Price? = null,
        @Json(name = "change")
        val change: String? = null
    ) {
        @JsonClass(generateAdapter = true)
        data class Price(
            @Json(name = "currency")
            val currency: String? = null,
            @Json(name = "amount")
            val amount: Int? = null
        )
    }

    @JsonClass(generateAdapter = true)
    data class City(
        @Json(name = "id")
        val id: String? = null,
        @Json(name = "name")
        val name: String? = null
    )

    @JsonClass(generateAdapter = true)
    data class Town(
        @Json(name = "id")
        val id: String? = null,
        @Json(name = "name")
        val name: String? = null
    )

    @JsonClass(generateAdapter = true)
    data class Brand(
        @Json(name = "id")
        val id: String? = null,
        @Json(name = "name")
        val name: String? = null,
        @Json(name = "imageUrl")
        val imageUrl: String? = null
    )

    @JsonClass(generateAdapter = true)
    data class Model(
        @Json(name = "id")
        val id: String? = null,
        @Json(name = "name")
        val name: String? = null,
        @Json(name = "imageUrl")
        val imageUrl: String? = null,
        @Json(name = "vehicleType")
        val vehicleType: Vehicle? = null
    )
    {
        @JsonClass(generateAdapter = true)
        data class Vehicle(
            @Json(name = "id")
            val id: String? = null,
            @Json(name = "name")
            val name: String? = null,
            @Json(name = "imageUrl")
            val imageUrl: String? = null
        )
    }

    @JsonClass(generateAdapter = true)
    data class Media(
        @Json(name = "id")
        val id: String? = null,
        @Json(name = "isCover")
        val isCover: Boolean? = null,
        @Json(name = "order")
        val order: Int? = null,
        @Json(name = "url")
        val url: String? = null,
        @Json(name = "type")
        val type: String? = null
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
        @Json(name = "text")
        val text: String? = null
    )

    @JsonClass(generateAdapter = true)
    data class AdditionalMetadata(
        @Json(name = "id")
        val id: String? = null,
        @Json(name = "metadataTypeId")
        val metadataTypeId: String? = null,
        @Json(name = "metadataType")
        val metadataType: String? = null,
        @Json(name = "metadataValueId")
        val metadataValueId: String? = null,
        @Json(name = "metadataValue")
        val metadataValue: String? = null
    )

    @JsonClass(generateAdapter = true)
    data class FeaturedMetadata(
        @Json(name = "id")
        val id: String? = null,
        @Json(name = "metadataTypeId")
        val metadataTypeId: String? = null,
        @Json(name = "metadataType")
        val metadataType: String? = null,
        @Json(name = "text")
        val text: String? = null,
        @Json(name = "metadataValueId")
        val metadataValueId: String? = null,
        @Json(name = "metadataValue")
        val metadataValue: String? = null
    )
}