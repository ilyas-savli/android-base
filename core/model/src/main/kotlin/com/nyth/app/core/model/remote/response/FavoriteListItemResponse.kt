package com.nyth.app.core.model.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FavoriteListItemResponse(
    @Json(name = "id")
    val id: String,
    @Json(name = "classifiedAdNumber")
    val classifiedAdNumber: String? = null,
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "slug")
    val slug: String? = null,
    @Json(name = "imageUrl")
    val imageUrl: String? = null,
    @Json(name = "shareUrl")
    val shareUrl: String? = null,
    @Json(name = "categoryType")
    val categoryType: String? = null,
    @Json(name = "categoryName")
    val categoryName: String? = null,
    @Json(name = "price")
    val price: Price? = null,
    @Json(name = "location")
    val location: String? = null,
    @Json(name = "acceleratePlaces")
    val acceleratePlaces: List<String>? = null,
    @Json(name = "publicationDate")
    val publicationDate: String? = null,
    @Json(name = "publicationEndDate")
    val publicationEndDate: String? = null,
    @Json(name = "publicationEndDay")
    val publicationEndDay: Int? = null,
    @Json(name = "impressionCount")
    val impressionCount: Int? = null,
    @Json(name = "favoriteCount")
    val favoriteCount: Int? = null,
    @Json(name = "conversationCount")
    val conversationCount: Int? = null,
    @Json(name = "reason")
    val reason: String? = null,
    @Json(name = "status")
    val status: String? = null,
    @Json(name = "metadata")
    val metadata: List<Metadata>? = null,
) {
    @JsonClass(generateAdapter = true)
    data class Metadata(
        @Json(name = "metadataTypeId")
        val metadataTypeId: String? = null,
        @Json(name = "metadataType")
        val metadataType: String? = null,
        @Json(name = "metadataValue")
        val metadataValue: String? = null
    )

    @JsonClass(generateAdapter = true)
    data class Price(
        @Json(name = "currency")
        val currency: String? = null,
        @Json(name = "amount")
        val amount: Double? = null
    )
}