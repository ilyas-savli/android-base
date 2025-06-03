package com.nyth.app.core.model.local

import com.nyth.app.core.model.remote.response.AccountListingResponse
import com.nyth.app.core.model.remote.response.FavoriteListItemResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListingItemModel(
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
    val acceleratePlaces: List<AcceleratePlace>? = null,
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
    val status: ListingStatus? = null,
    @Json(name = "metadata")
    val metadata: List<Metadata>? = null,
) {
    enum class AcceleratePlace {
        @Json(name = "MakeDifferent")
        MakeDifferent,

        @Json(name = "ShowInExplore")
        ShowInExplore,

        @Json(name = "ShowAtTop")
        ShowAtTop,

        @Json(name = "Showcase")
        Showcase,

        @Json(name = "ShowInCategoryPage")
        ShowInCategoryPage
    }

    enum class ListingStatus {
        @Json(name = "Draft")
        Draft,

        @Json(name = "PendingReview")
        PendingReview,

        @Json(name = "Active")
        Active,

        @Json(name = "Passive")
        Passive,

        @Json(name = "Rejected")
        Rejected,

        @Json(name = "Ended")
        Ended,

        @Json(name = "Deleted")
        Deleted,

        @Json(name = "DeletedDraft")
        DeletedDraft
    }

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

    companion object {
        fun transform(responses: List<AccountListingResponse?>?): List<ListingItemModel>? {
            return responses?.mapNotNull { transform(it) }
        }

        fun transform(response: AccountListingResponse?): ListingItemModel? {
            if (response == null) return null
            return ListingItemModel(
                id = response.id,
                classifiedAdNumber = response.classifiedAdNumber,
                title = response.title,
                description = response.description,
                slug = response.slug,
                imageUrl = response.imageUrl,
                categoryType = response.categoryType,
                categoryName = response.categoryName,
                price = Price(
                    currency = response.price?.currency,
                    amount = response.price?.amount
                ),
                location = response.location,
                acceleratePlaces = response.acceleratePlaces?.map { AcceleratePlace.valueOf(it) },
                publicationDate = response.publicationDate,
                publicationEndDate = response.publicationEndDate,
                publicationEndDay = response.publicationEndDay,
                impressionCount = response.impressionCount,
                favoriteCount = response.favoriteCount,
                conversationCount = response.conversationCount,
                reason = response.reason,
                status = response.status?.let { ListingStatus.valueOf(it) },
                metadata = response.metadata?.map {
                    Metadata(
                        metadataTypeId = it.metadataTypeId,
                        metadataType = it.metadataType,
                        metadataValue = it.metadataValue
                    )
                }
            )
        }


        fun transformFavorite(responses: List<FavoriteListItemResponse?>?): List<ListingItemModel>? {
            return responses?.mapNotNull { transformFavorite(it) }
        }

        fun transformFavorite(response: FavoriteListItemResponse?): ListingItemModel? {
            if (response == null) return null
            return ListingItemModel(
                id = response.id,
                classifiedAdNumber = response.classifiedAdNumber,
                title = response.title,
                description = response.description,
                slug = response.slug,
                imageUrl = response.imageUrl,
                shareUrl = response.shareUrl,
                categoryType = response.categoryType,
                categoryName = response.categoryName,
                price = Price(
                    currency = response.price?.currency,
                    amount = response.price?.amount
                ),
                location = response.location,
                acceleratePlaces = response.acceleratePlaces?.map { AcceleratePlace.valueOf(it) },
                publicationDate = response.publicationDate,
                publicationEndDate = response.publicationEndDate,
                publicationEndDay = response.publicationEndDay,
                impressionCount = response.impressionCount,
                favoriteCount = response.favoriteCount,
                conversationCount = response.conversationCount,
                reason = response.reason,
                status = response.status?.let { ListingStatus.valueOf(it) },
                metadata = response.metadata?.map {
                    Metadata(
                        metadataTypeId = it.metadataTypeId,
                        metadataType = it.metadataType,
                        metadataValue = it.metadataValue
                    )
                }
            )
        }
    }
}
