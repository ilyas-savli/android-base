package com.nyth.app.core.model.remote.response

import androidx.room.Ignore
import com.nyth.app.core.model.ext.BooleanExt.safeGet
import com.nyth.app.core.model.local.ListingStatusInfo
import com.nyth.app.core.model.local.enums.ListingCategoryType
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListingPreviewResponse(
    @Json(name = "id") val id: String? = null,
    @Json(name = "classifiedAdNumber") val classifiedAdNumber: Int? = null,
    @Json(name = "slug") val slug: String? = null,
    @Json(name = "title") val title: String? = null,
    @Json(name = "description") val description: String? = null,
    @Json(name = "price") val price: Price? = null,
    @Json(name = "communicationPreference") val communicationPreference: String? = null,
    @Json(name = "status") val status: String? = null,
    @Json(name = "category") val category: Category? = null,
    @Json(name = "city") val city: City? = null,
    @Json(name = "town") val town: Town? = null,
    @Json(name = "brand") val brand: Brand? = null,
    @Json(name = "model") val model: Model? = null,
    @Json(name = "media") val media: List<Media>? = null,
    @Json(name = "metadata") val metadata: List<Metadata>? = null,
    @Json(name = "additionalMetadata") val additionalMetadata: List<AdditionalMetadata>? = null,
    @Json(name = "featuredMetadata") val featuredMetadata: List<FeaturedMetadata>? = null,
    @Ignore var shouldUpdate: Boolean = false,
    @Ignore var hasAdditionalMetadata: Boolean? = null
) {
    @JsonClass(generateAdapter = true)
    data class Price(
        @Json(name = "currency") val currency: String? = null,
        @Json(name = "amount") val amount: Int? = null
    )

    @JsonClass(generateAdapter = true)
    data class Category(
        @Json(name = "id") val id: String? = null,
        @Json(name = "name") val name: String? = null,
        @Json(name = "categoryType") val categoryType: String? = null,
        @Json(name = "mainCategoryId") val mainCategoryId: String? = null,
        @Ignore var isSubCategoryFilled: Boolean = false
    )

    @JsonClass(generateAdapter = true)
    data class City(
        @Json(name = "id") val id: String? = null, @Json(name = "name") val name: String? = null
    )

    @JsonClass(generateAdapter = true)
    data class Town(
        @Json(name = "id") val id: String? = null, @Json(name = "name") val name: String? = null
    )

    @JsonClass(generateAdapter = true)
    data class Brand(
        @Json(name = "id") val id: String? = null,
        @Json(name = "name") val name: String? = null,
        @Json(name = "imageUrl") val imageUrl: String? = null
    )

    @JsonClass(generateAdapter = true)
    data class Model(
        @Json(name = "id") val id: String? = null,
        @Json(name = "name") val name: String? = null,
        @Json(name = "imageUrl") val imageUrl: String? = null
    )

    @JsonClass(generateAdapter = true)
    data class Media(
        @Json(name = "id") val id: String? = null,
        @Json(name = "isCover") val isCover: Boolean? = null,
        @Json(name = "order") val order: Int? = null,
        @Json(name = "url") val url: String? = null,
        @Json(name = "type") val type: String? = null
    )

    @JsonClass(generateAdapter = true)
    data class Metadata(
        @Json(name = "id") val id: String? = null,
        @Json(name = "metadataTypeId") val metadataTypeId: String? = null,
        @Json(name = "metadataType") val metadataType: String? = null,
        @Json(name = "metadataValueId") val metadataValueId: String? = null,
        @Json(name = "metadataValue") val metadataValue: String? = null,
        @Json(name = "text") var text: String? = null
    )

    @JsonClass(generateAdapter = true)
    data class AdditionalMetadata(
        @Json(name = "id") val id: String? = null,
        @Json(name = "metadataTypeId") val metadataTypeId: String? = null,
        @Json(name = "metadataType") val metadataType: String? = null,
        @Json(name = "metadataValueId") val metadataValueId: String? = null,
        @Json(name = "metadataValue") val metadataValue: String? = null,
        @Json(name = "text") val text: String? = null
    )

    @JsonClass(generateAdapter = true)
    data class FeaturedMetadata(
        @Json(name = "id") val id: String? = null,
        @Json(name = "metadataTypeId") val metadataTypeId: String? = null,
        @Json(name = "metadataType") val metadataType: String? = null,
        @Json(name = "text") val text: String? = null,
        @Json(name = "metadataValueId") val metadataValueId: String? = null,
        @Json(name = "metadataValue") val metadataValue: String? = null
    )

    fun getListingStatusInfo(): ListingStatusInfo {
        val info = ListingStatusInfo()
        val categoryType = ListingCategoryType.find(type = category?.categoryType)
        // first state
        if (categoryType != ListingCategoryType.Motor) {
            info.firstStateMax++
            if (category?.isSubCategoryFilled.safeGet() || !id.isNullOrEmpty()) {
                info.firstStateFinish++
                //info.firstStateDestination = Destination.ListingAddAdvertBrand
            } else {
                //info.firstStateDestination = Destination.ListingAddAdvertSubCategory
            }
        }
        info.firstStateMax++
        if (brand != null) {
            info.firstStateFinish++
            /*info.firstStateDestination = when (categoryType) {
                ListingCategoryType.Motor -> Destination.ListingAddAdvertModel
                else -> Destination.ListingAddAdvertCity
            }*/
        }
        if (categoryType == ListingCategoryType.Motor) {
            info.firstStateMax++
            if (model != null) {
                info.firstStateFinish++
                //info.firstStateDestination = Destination.ListingAddAdvertCity
            }
        }
        info.firstStateMax++
        if (city != null) {
            info.firstStateFinish++
            //info.firstStateDestination = Destination.ListingAddAdvertDistrict
        }
        info.firstStateMax++
        if (town != null) {
            info.firstStateFinish++
            //info.firstStateDestination = Destination.ListingAddAdvertCommunicationType
        }
        info.firstStateMax++
        if (communicationPreference != null) {
            info.firstStateFinish++
            //info.firstStateDestination = Destination.ListingAddAdvertBrand
        }
        info.firstStageProgress = info.firstStateFinish.toFloat() / info.firstStateMax.toFloat()

        // second state
        info.secondStateMax++
        if (metadata != null) {
            info.secondStateFinish++
            //info.secondStateDestination = Destination.ListingAddAdvertExtraDetail
        }

        // Additonal Metadata yoksa ekleme yapma
        if (hasAdditionalMetadata.safeGet(true)) {
            info.secondStateMax++
            if (additionalMetadata != null) {
                info.secondStateFinish++
                //info.secondStateDestination = Destination.ListingAddAdvertDetail
            }
        }
        info.secondStageProgress = info.secondStateFinish.toFloat() / info.secondStateMax.toFloat()

        // third state
        info.thirdStateMax++
        if (title != null) {
            info.thirdStateFinish++
            //info.thirdStateDestination = Destination.ListingAddMedia
        }
        info.thirdStateMax++
        if (!media.isNullOrEmpty()) {
            info.thirdStateFinish++
            //info.thirdStateDestination = Destination.ListingAddAdvertInformation
        }
        info.thirdStageProgress = info.thirdStateFinish.toFloat() / info.thirdStateMax.toFloat()
        return info
    }
}