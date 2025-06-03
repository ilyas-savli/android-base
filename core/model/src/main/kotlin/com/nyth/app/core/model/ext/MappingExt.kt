package com.nyth.app.core.model.ext

import com.nyth.app.core.model.addadvert.SelectedMedia
import com.nyth.app.core.model.ext.BooleanExt.safeGet
import com.nyth.app.core.model.local.enums.MediaType
import com.nyth.app.core.model.remote.request.ListingAddRequest
import com.nyth.app.core.model.remote.response.BrandModelsResponse
import com.nyth.app.core.model.remote.response.BrandsResponse
import com.nyth.app.core.model.remote.response.CategoryResponse
import com.nyth.app.core.model.remote.response.CityResponse
import com.nyth.app.core.model.remote.response.DistrictResponse
import com.nyth.app.core.model.remote.response.ListingPreviewResponse
import com.nyth.app.core.model.remote.response.RefreshTokenResponse
import com.nyth.app.core.model.remote.response.SubCategoryResponse
import com.nyth.app.core.model.remote.response.TokenResponse

object MappingExt {
    fun RefreshTokenResponse?.toTokenResponse() = TokenResponse(
        accessToken = this?.accessToken,
        expiresIn = this?.expiresIn,
        tokenType = this?.tokenType,
        refreshToken = this?.refreshToken,
        scope = this?.scope
    )

    fun ListingPreviewResponse.toListingAddRequest() = ListingAddRequest(
        categoryId = category?.id,
        brandId = brand?.id,
        modelId = model?.id,
        cityId = city?.id,
        townId = town?.id,
        communicationPreference = communicationPreference,
        price = ListingAddRequest.Price(
            currency = price?.currency,
            amount = price?.amount
        ),
        description = description,
        title = title,
        details = toDetails()
    )

    fun CategoryResponse.toPreviewCategory(isSubCategoryFilled: Boolean = false) =
        ListingPreviewResponse.Category(
            id = id,
            name = name,
            categoryType = categoryType,
            isSubCategoryFilled = isSubCategoryFilled
        )

    fun SubCategoryResponse.toPreviewCategory(isSubCategoryFilled: Boolean = false) =
        ListingPreviewResponse.Category(
            id = id,
            name = name,
            categoryType = categoryType,
            isSubCategoryFilled = isSubCategoryFilled,
            mainCategoryId = parentCategoryId
        )

    fun BrandsResponse.toPreviewBrand() = ListingPreviewResponse.Brand(
        id = id, name = name
    )

    fun BrandModelsResponse.toPreviewModel() = ListingPreviewResponse.Model(
        id = id, name = name
    )

    fun CityResponse.toPreviewCity() = ListingPreviewResponse.City(
        id = id, name = name
    )

    fun DistrictResponse.toPreviewTown() = ListingPreviewResponse.Town(
        id = id, name = name
    )

    fun ListingAddRequest.Price.toPreviewPrice() = ListingPreviewResponse.Price(
        currency = currency, amount = amount
    )

    private fun ListingPreviewResponse.toDetails(): List<ListingAddRequest.Detail> {
        val details = mutableListOf<ListingAddRequest.Detail>()
        metadata?.forEach {
            details.add(
                ListingAddRequest.Detail(
                    typeId = it.metadataTypeId, value = it.metadataValueId ?: it.text
                )
            )
        }
        additionalMetadata?.forEach {
            details.add(
                ListingAddRequest.Detail(
                    typeId = it.metadataTypeId, value = it.metadataValueId ?: it.text
                )
            )
        }
        return details
    }

    fun List<ListingPreviewResponse.Media>.toSelectedMediaList(): List<SelectedMedia> = map {
        SelectedMedia(
            mediaId = it.id,
            mediaUrl = it.url,
            mediaType = MediaType.fromString(type = it.type),
            isUploaded = it.url?.contains("https").safeGet(),
            isCover = it.isCover.safeGet(),
            order = it.order,
            isAsMotorVoice = MediaType.fromString(type = it.type) == MediaType.EngineVideo
        )
    }
}