package com.nyth.app.core.network.client

import com.nyth.app.core.model.remote.request.ConversationComplainRequest
import com.nyth.app.core.model.remote.request.ConversationMessageRequest
import com.nyth.app.core.model.remote.request.ConversationRequest
import com.nyth.app.core.model.remote.request.ListingAddRequest
import com.nyth.app.core.model.remote.request.ListingFilterRequest
import com.nyth.app.core.model.remote.request.ListingMediaPutRequest
import com.nyth.app.core.model.remote.request.PostAcceleratePackageRequest
import com.nyth.app.core.model.remote.request.PostFavoriteListsRequest
import com.nyth.app.core.model.remote.request.PostListingComplaintRequest
import com.nyth.app.core.model.remote.request.PutAcceleratePackageRequest
import com.nyth.app.core.model.remote.request.PutChangeListingStatusRequest
import com.nyth.app.core.model.remote.request.PutEvaluateRequest
import com.nyth.app.core.model.remote.request.PutFavoriteListsRequest
import com.nyth.app.core.model.remote.response.BaseResponse
import com.nyth.app.core.model.remote.response.BrandsResponse
import com.nyth.app.core.model.remote.response.FeaturedBrandResponse
import com.nyth.app.core.network.service.ListingService
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import javax.inject.Inject

class ListingClient @Inject constructor(
    private val listingService: ListingService
) {
    // region AcceleratePackagePrice
    suspend fun getAcceleratePrice(categoryId: String) =
        listingService.getAcceleratePrice(categoryId = categoryId)

    suspend fun getAcceleratePackageList(
        classifiedAdId: String
    ) = listingService.getAcceleratePackageList(classifiedAdId = classifiedAdId)

    suspend fun putAcceleratePrice(acceleratePriceId: String): Response<Unit> =
        listingService.putAcceleratePrice(acceleratePriceId = acceleratePriceId)

    // endregion

    // region Brand
    suspend fun getBrands(categoryId: String): Response<BaseResponse<List<BrandsResponse>>> =
        listingService.getBrands(categoryId = categoryId)

    suspend fun getBrandFeatured(categoryId: String): Response<BaseResponse<List<FeaturedBrandResponse>>> =
        listingService.getBrandFeatured(categoryId = categoryId)

    suspend fun getBrandDetail(brandId: String): Response<Unit> =
        listingService.getBrandDetail(brandId = brandId)

    suspend fun postBrand(): Response<Unit> =
        listingService.postBrand()

    suspend fun putBrand(brandId: String): Response<Unit> =
        listingService.putBrand(brandId = brandId)

    suspend fun deleteBrand(brandId: String): Response<Unit> =
        listingService.deleteBrand(brandId = brandId)

    suspend fun putBrandFeatured(brandId: String): Response<Unit> =
        listingService.putBrandFeatured(brandId = brandId)

    // endregion

    // region BrandModel
    suspend fun getBrandModels(brandId: String) =
        listingService.getBrandModels(brandId = brandId)

    suspend fun postBrandModel(brandId: String): Response<Unit> =
        listingService.postBrandModel(brandId = brandId)

    suspend fun getBrandModelDetails(brandModelId: String): Response<Unit> =
        listingService.getBrandModelDetails(brandModelId = brandModelId)

    suspend fun putBrandModel(brandModelId: String): Response<Unit> =
        listingService.putBrandModel(brandModelId = brandModelId)

    suspend fun getBrandModelsPopular(categoryType: String, brandId: String) =
        listingService.getBrandModelsPopular(categoryType = categoryType, brandId = brandId)

    // endregion

    // region Category
    suspend fun getCategories() =
        listingService.getCategories()

    suspend fun postCategories(): Response<Unit> =
        listingService.postCategories()

    suspend fun getCategoriesById(categoryId: String) =
        listingService.getCategoriesById(categoryId = categoryId)

    suspend fun getCategoriesPopular(categoryType: String) =
        listingService.getCategoriesPopular(categoryType = categoryType)

    // endregion

    // region City
    suspend fun getCities() =
        listingService.getCities()

    suspend fun getTowns(cityId: String) =
        listingService.getTowns(cityId = cityId)
    // endregion

    // region BrandModel

    // endregion

    // region ClassifiedAd

    suspend fun postListing(request: ListingAddRequest) =
        listingService.postListing(request = request)

    suspend fun putListing(listingId: String, request: ListingAddRequest) =
        listingService.putListing(listingId = listingId, request = request)

    suspend fun getListingDraft() =
        listingService.getListingDraft()

    suspend fun putListingComplete(listingId: String) =
        listingService.putListingComplete(listingId = listingId)

    suspend fun deleteListingDraft() =
        listingService.deleteListingDraft()

    suspend fun deleteListing(classifiedAdId: String) =
        listingService.deleteListing(classifiedAdId = classifiedAdId)

    suspend fun changeStatus(
        classifiedAdId: String,
        request: PutChangeListingStatusRequest
    ) = listingService.changeStatus(
        classifiedAdId = classifiedAdId,
        request = request
    )

    suspend fun getListingSearch(searchTerm: String) =
        listingService.getListingSearch(searchTerm = searchTerm)

    suspend fun getListingDetail(classifiedAdNumber: Int) =
        listingService.getListingDetail(classifiedAdNumber = classifiedAdNumber)

    suspend fun getListingPreview(listingId: String) =
        listingService.getListingPreview(listingId = listingId)
    suspend fun getListingWithClassifiedAdId(listingId: String) =
        listingService.getListingWithClassifiedAdId(listingId = listingId)

    suspend fun getListingShowCase(vehicleTypeId: String) =
        listingService.getListingShowCase(vehicleTypeId = vehicleTypeId)

    suspend fun postAcceleratePackageList(
        classifiedAdId: String,
        request: PostAcceleratePackageRequest
    ) = listingService.postAcceleratePackageList(
        classifiedAdId = classifiedAdId,
        request = request
    )

    suspend fun putAcceleratePackageList(
        classifiedAdId: String,
        request: PutAcceleratePackageRequest
    ) = listingService.putAcceleratePackageList(
        classifiedAdId = classifiedAdId,
        request = request
    )

    suspend fun deleteAcceleratePackageList(
        classifiedAdId: String,
        acceleratePackagePriceId: String
    ) = listingService.deleteAcceleratePackageList(
        classifiedAdId = classifiedAdId,
        acceleratePackagePriceId = acceleratePackagePriceId
    )

    suspend fun postListingMedia(
        listingId: String,
        isCover: RequestBody,
        order: RequestBody,
        file: MultipartBody.Part,
        mediaType: RequestBody
    ) = listingService.postListingMedia(
        listingId = listingId,
        isCover = isCover,
        order = order,
        file = file,
        mediaType = mediaType
    )

    suspend fun putListingMedia(
        listingId: String,
        mediaId: String,
        request: ListingMediaPutRequest
    ) = listingService.putListingMedia(
        listingId = listingId,
        mediaId = mediaId,
        request = request
    )

    suspend fun deleteListingMedia(
        listingId: String,
        mediaId: String
    ) = listingService.deleteListingMedia(
        listingId = listingId,
        mediaId = mediaId
    )

    suspend fun getListingSimilarListings(
        classifiedAdId: String,
        brandId: String,
        brandModelId: String,
        vehicleTypeId: String
    ) =
        listingService.getListingSimilarListings(
            classifiedAdId = classifiedAdId,
            brandId = brandId,
            brandModelId = brandModelId,
            vehicleTypeId = vehicleTypeId
        )

    suspend fun getAccountListings(
        isActive: Boolean?,
        orderBy: String?,
        categoryType: String?,
        pageIndex: Int?,
        pageSize: Int?
    ) = listingService.getAccountListings(
        isActive = isActive,
        orderBy = orderBy,
        categoryType = categoryType,
        pageIndex = pageIndex,
        pageSize = pageSize
    )

    suspend fun getAccountListingsByAccountID(
        accountId: String?, isActive: Boolean?,
        orderBy: String?,
        categoryType: String?,
        pageIndex: Int?,
        pageSize: Int?
    ) = listingService.getAccountListingsByAccountID(
        accountId = accountId,
        isActive = isActive,
        orderBy = orderBy,
        categoryType = categoryType,
        pageIndex = pageIndex,
        pageSize = pageSize
    )

    suspend fun getListingRelatedLinks(
        categoryId: String,
        brandId: String,
        brandModelId: String
    ) = listingService.getListingRelatedLinks(
        categoryId = categoryId,
        brandId = brandId,
        brandModelId = brandModelId)

    suspend fun postListingComplaint(
        request: PostListingComplaintRequest
    ) = listingService.postListingComplaint(request = request)

    // endregion

    // region MetadataForm

    suspend fun getMetadataFormsById(categoryId: String, formType: String) =
        listingService.getMetadataFormsById(categoryId = categoryId, formType = formType)

    suspend fun postMetadataFormsById(categoryId: String) =
        listingService.postMetadataFormsById(categoryId = categoryId)

    suspend fun postListingFilter(request: ListingFilterRequest) =
        listingService.postListingFilter(request = request)

    // endregion

    // region ClassifiedAdFilters

    suspend fun getListingFilters(filterType: String) =
        listingService.getListingFilters(filterType = filterType)

    // endregion

    // region FavoriteClassifiedAd
    suspend fun getFavoriteLists() =
        listingService.getFavoriteLists()
    suspend fun getFavoriteList(listId: String) =
        listingService.getFavoriteList(listId = listId)

    suspend fun getFavoriteListItems(listId: String) =
        listingService.getFavoriteListItems(listId = listId)

    suspend fun postFavoriteLists(request: PutFavoriteListsRequest) =
        listingService.postFavoriteLists(request = request)

    suspend fun postFavoriteListsByListId(listId: String, request: PostFavoriteListsRequest) =
        listingService.postFavoriteListsByListId(listId = listId, request = request)

    suspend fun deleteFavoriteAdvertByListId(listingId: String) =
        listingService.deleteFavoriteAdvertByListId(listingId = listingId)

    suspend fun deleteFavoriteList(listId: String) =
        listingService.deleteFavoriteList(listId = listId)

    suspend fun putFavoriteListByListId(listId: String, request: PutFavoriteListsRequest) =
        listingService.putFavoriteListByListId(listId = listId, request = request)
    // endregion

    // region MotorcycleComparison
    suspend fun getMotorcycleComparison(pageIndex: Int, pageSize: Int) =
        listingService.getMotorcycleComparison(pageIndex = pageIndex, pageSize = pageSize)

    suspend fun getMotorcycleComparisonBrands() =
        listingService.getMotorcycleComparisonBrands()

    suspend fun getMotorcycleComparisonBrandModels(brandId: String) =
        listingService.getMotorcycleComparisonBrandModels(brandId = brandId)

    suspend fun getMotorcycleComparisonSummary(motorcycleModelIds: List<String>) =
        listingService.getMotorcycleComparisonSummary(motorcycleModelIds = motorcycleModelIds)

    suspend fun getMotorcycleComparisonCompare(motorcycleIds: List<String>) =
        listingService.getMotorcycleComparisonCompare(motorcycleIds = motorcycleIds)

    suspend fun putMotorcycleComparisonVote(
        motorcycleComparisonId: String,
        request: PutEvaluateRequest
    ) =
        listingService.putMotorcycleComparisonVote(
            motorcycleComparisonId = motorcycleComparisonId,
            request = request
        )

    // endregion

    // region VehicleType
    suspend fun getVehicleType() = listingService.getVehicleType()
    // endregion

    // region PaymentSummary
    suspend fun getPaymentSummary(
        classifiedAdId: String
    ) = listingService.getPaymentSummary(
        classifiedAdId = classifiedAdId
    )
    // endregion

    // region Conversations
    suspend fun getConversations() = listingService.getConversations()

    suspend fun postConversationsByClassifiedAdId(
        conversationRequest: ConversationRequest
    ) = listingService.postConversationsByClassifiedAdId(conversationRequest)

    suspend fun getConversationByConversationId(
        conversationId: String
    ) = listingService.getConversationByConversationId(conversationId)

    suspend fun deleteConversationById(
        conversationId: String
    ) = listingService.deleteConversationById(conversationId)

    suspend fun getConversationMessagesById(
        conversationId: String,
        pageIndex: Int,
        pageSize: Int
    ) = listingService.getConversationMessagesById(
        conversationId = conversationId,
        pageIndex = pageIndex,
        pageSize = pageSize
    )

    suspend fun postConversationSendMessageById(
        conversationId: String,
        conversationMessageRequest: ConversationMessageRequest
    ) = listingService.postConversationSendMessageById(
        conversationId = conversationId,
        conversationMessageRequest = conversationMessageRequest
    )

    suspend fun putConversationComplainById(
        conversationId: String,
        conversationComplainRequest: ConversationComplainRequest
    ) = listingService.putConversationComplainById(
        conversationId = conversationId,
        conversationComplainRequest = conversationComplainRequest
    )

    // endregion

}
