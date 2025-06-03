package com.nyth.app.core.network.repository

import com.nyth.app.core.database.EncryptedDataStoreManager
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
import com.nyth.app.core.network.client.ListingClient
import com.nyth.app.core.network.utils.NetworkHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class ListingRepository @Inject constructor(
    private val listingClient: ListingClient,
    private val ioDispatcher: CoroutineDispatcher,
    private val networkHandler: NetworkHandler,
    private val dataStore: EncryptedDataStoreManager
) {
    // region AcceleratePackagePrice
    suspend fun getAcceleratePrice(categoryId: String) =
        networkHandler.handleResponse(request = {
            listingClient.getAcceleratePrice(categoryId = categoryId)
        }).flowOn(ioDispatcher)

    suspend fun getAcceleratePackageList(classifiedAdId: String) =
        networkHandler.handleResponse(request = {
            listingClient.getAcceleratePackageList(classifiedAdId = classifiedAdId)
        }).flowOn(ioDispatcher)

    suspend fun putAcceleratePrice(acceleratePriceId: String) =
        networkHandler.handleResponse(request = {
            listingClient.putAcceleratePrice(acceleratePriceId = acceleratePriceId)
        }).flowOn(ioDispatcher)

    // endregion

    // region Brand
    suspend fun getBrands(categoryId: String) =
        networkHandler.handleResponse(request = {
            listingClient.getBrands(categoryId = categoryId)
        }).flowOn(ioDispatcher)

    suspend fun getBrandFeatured(categoryId: String) =
        networkHandler.handleResponse(request = {
            listingClient.getBrandFeatured(categoryId = categoryId)
        }).flowOn(ioDispatcher)

    suspend fun getBrandDetail(brandId: String) =
        networkHandler.handleResponse(request = {
            listingClient.getBrandDetail(brandId = brandId)
        }).flowOn(ioDispatcher)

    suspend fun postBrand() =
        networkHandler.handleResponse(request = {
            listingClient.postBrand()
        }).flowOn(ioDispatcher)

    suspend fun putBrand(brandId: String) =
        networkHandler.handleResponse(request = {
            listingClient.putBrand(brandId = brandId)
        }).flowOn(ioDispatcher)

    suspend fun deleteBrand(brandId: String) =
        networkHandler.handleResponse(request = {
            listingClient.deleteBrand(brandId = brandId)
        }).flowOn(ioDispatcher)

    suspend fun putBrandFeatured(brandId: String) =
        networkHandler.handleResponse(request = {
            listingClient.putBrandFeatured(brandId = brandId)
        }).flowOn(ioDispatcher)

    // endregion

    // region BrandModel
    suspend fun getBrandModels(brandId: String) =
        networkHandler.handleResponse(request = {
            listingClient.getBrandModels(brandId = brandId)
        }).flowOn(ioDispatcher)

    suspend fun postBrandModel(brandId: String) =
        networkHandler.handleResponse(request = {
            listingClient.postBrandModel(brandId = brandId)
        }).flowOn(ioDispatcher)

    suspend fun getBrandModelDetails(brandModelId: String) =
        networkHandler.handleResponse(request = {
            listingClient.getBrandModelDetails(brandModelId = brandModelId)
        }).flowOn(ioDispatcher)

    suspend fun putBrandModel(brandModelId: String) =
        networkHandler.handleResponse(request = {
            listingClient.putBrandModel(brandModelId = brandModelId)
        }).flowOn(ioDispatcher)

    suspend fun getBrandModelsPopular(categoryType: String, brandId: String) =
        networkHandler.handleResponse(request = {
            listingClient.getBrandModelsPopular(categoryType = categoryType, brandId = brandId)
        }).flowOn(ioDispatcher)

    // endregion

    // region Category
    suspend fun getCategories() =
        networkHandler.handleResponse(request = {
            listingClient.getCategories()
        }).flowOn(ioDispatcher)

    suspend fun postCategories() =
        networkHandler.handleResponse(request = {
            listingClient.postCategories()
        }).flowOn(ioDispatcher)

    suspend fun getCategoriesById(categoryId: String) =
        networkHandler.handleResponse(request = {
            listingClient.getCategoriesById(categoryId = categoryId)
        }).flowOn(ioDispatcher)

    suspend fun getCategoriesPopular(categoryType: String) =
        networkHandler.handleResponse(request = {
            listingClient.getCategoriesPopular(categoryType = categoryType)
        }).flowOn(ioDispatcher)
    // endregion

    // region City
    suspend fun getCities() =
        networkHandler.handleResponse(request = {
            listingClient.getCities()
        }).flowOn(ioDispatcher)

    suspend fun getTowns(cityId: String) =
        networkHandler.handleResponse(request = {
            listingClient.getTowns(cityId = cityId)
        }).flowOn(ioDispatcher)

    // endregion

    // region BrandModel


    // endregion

    // region ClassifiedAd
    suspend fun postListing(request: ListingAddRequest) =
        networkHandler.handleResponse(request = {
            listingClient.postListing(request = request)
        }).flowOn(ioDispatcher)

    suspend fun putListing(listingId: String, request: ListingAddRequest) =
        networkHandler.handleResponse(request = {
            listingClient.putListing(listingId = listingId, request = request)
        }).flowOn(ioDispatcher)

    suspend fun getListingDraft() =
        networkHandler.handleResponse(request = {
            listingClient.getListingDraft()
        }).flowOn(ioDispatcher)

    suspend fun putListingComplete(listingId: String) =
        networkHandler.handleResponse {
            listingClient.putListingComplete(listingId = listingId)
        }

    suspend fun deleteListingDraft() =
        networkHandler.handleResponse(request = {
            listingClient.deleteListingDraft()
        }).flowOn(ioDispatcher)

    suspend fun deleteListing(classifiedAdId: String) =
        networkHandler.handleResponse(request = {
            listingClient.deleteListing(classifiedAdId = classifiedAdId)
        }).flowOn(ioDispatcher)

    suspend fun changeStatus(
        classifiedAdId: String,
        request: PutChangeListingStatusRequest
    ) = networkHandler.handleResponse(request = {
        listingClient.changeStatus(
            classifiedAdId = classifiedAdId,
            request = request
        )
    }).flowOn(ioDispatcher)

    suspend fun getListingSearch(searchTerm: String) =
        networkHandler.handleResponse(request = {
            listingClient.getListingSearch(searchTerm = searchTerm)
        }).flowOn(ioDispatcher)

    suspend fun getListingDetail(classifiedAdNumber: Int) =
        networkHandler.handleResponse(request = {
            listingClient.getListingDetail(classifiedAdNumber = classifiedAdNumber)
        }).flowOn(ioDispatcher)

    suspend fun getListingPreview(listingId: String) =
        networkHandler.handleResponse(request = {
            listingClient.getListingPreview(listingId = listingId)
        }).flowOn(ioDispatcher)
    suspend fun getListingWithClassifiedAdId(listingId: String) =
        networkHandler.handleResponse(request = {
            listingClient.getListingWithClassifiedAdId(listingId = listingId)
        }).flowOn(ioDispatcher)

    suspend fun getListingShowCase(vehicleTypeId: String) =
        networkHandler.handleResponse(request = {
            listingClient.getListingShowCase(vehicleTypeId = vehicleTypeId)
        }).flowOn(ioDispatcher)

    suspend fun postAcceleratePackageList(
        classifiedAdId: String,
        request: PostAcceleratePackageRequest
    ) = networkHandler.handleResponse(request = {
        listingClient.postAcceleratePackageList(
            classifiedAdId = classifiedAdId,
            request = request
        )
    }).flowOn(ioDispatcher)

    suspend fun putAcceleratePackageList(
        classifiedAdId: String,
        request: PutAcceleratePackageRequest
    ) = networkHandler.handleResponse(request = {
        listingClient.putAcceleratePackageList(
            classifiedAdId = classifiedAdId,
            request = request
        )
    }).flowOn(ioDispatcher)

    suspend fun deleteAcceleratePackageList(
        classifiedAdId: String,
        acceleratePackagePriceId: String
    ) = networkHandler.handleResponse(request = {
        listingClient.deleteAcceleratePackageList(
            classifiedAdId = classifiedAdId,
            acceleratePackagePriceId = acceleratePackagePriceId
        )
    }).flowOn(ioDispatcher)

    suspend fun postListingMedia(
        listingId: String,
        isCover: RequestBody,
        order: RequestBody,
        file: MultipartBody.Part,
        mediaType: RequestBody
    ) = networkHandler.handleResponse(request = {
        listingClient.postListingMedia(
            listingId = listingId,
            isCover = isCover,
            order = order,
            file = file,
            mediaType = mediaType
        )
    }).flowOn(ioDispatcher)

    suspend fun putListingMedia(
        listingId: String,
        mediaId: String,
        request: ListingMediaPutRequest
    ) = networkHandler.handleResponse(request = {
        listingClient.putListingMedia(
            listingId = listingId,
            mediaId = mediaId,
            request = request
        )
    }).flowOn(ioDispatcher)

    suspend fun deleteListingMedia(
        listingId: String,
        mediaId: String
    ) = networkHandler.handleResponse(request = {
        listingClient.deleteListingMedia(
            listingId = listingId,
            mediaId = mediaId
        )
    }).flowOn(ioDispatcher)

    suspend fun getListingSimilarListings(
        classifiedAdId:String,
        brandId: String,
        brandModelId: String,
        vehicleTypeId: String
    ) =
        networkHandler.handleResponse(request = {
            listingClient.getListingSimilarListings(
                classifiedAdId = classifiedAdId,
                brandId = brandId,
                brandModelId = brandModelId,
                vehicleTypeId = vehicleTypeId
            )
        }).flowOn(ioDispatcher)

    suspend fun getAccountListings(
        isActive: Boolean? = null,
        orderBy: String? = null,
        categoryType: String? = null,
        pageIndex: Int,
        pageSize: Int
    ) = networkHandler.handleResponse(request = {
        listingClient.getAccountListings(
            isActive = isActive,
            orderBy = orderBy,
            categoryType = categoryType,
            pageIndex = pageIndex,
            pageSize = pageSize
        )
    }).flowOn(ioDispatcher)

    suspend fun getAccountListingsByAccountID(
        accountId: String?,
        isActive: Boolean? = true,
        orderBy: String? = null,
        categoryType: String? = null,
        pageIndex: Int?,
        pageSize: Int?
    ) = networkHandler.handleResponse(request = {
        listingClient.getAccountListingsByAccountID(
            accountId = accountId,
            isActive = isActive,
            orderBy = orderBy,
            categoryType = categoryType,
            pageIndex = pageIndex,
            pageSize = pageSize
        )
    }).flowOn(ioDispatcher)

    suspend fun postListingFilter(request: ListingFilterRequest) =
        networkHandler.handleResponse(request = {
            listingClient.postListingFilter(request = request)
        }).flowOn(ioDispatcher)

    suspend fun getListingRelatedLinks(
        categoryId: String,
        brandId: String,
        brandModelId: String) =
        networkHandler.handleResponse(request = {
            listingClient.getListingRelatedLinks(
                categoryId = categoryId,
                brandId = brandId,
                brandModelId = brandModelId)
        }).flowOn(ioDispatcher)

    suspend fun postListingComplaint(request: PostListingComplaintRequest) =
        networkHandler.handleResponse(request = {
            listingClient.postListingComplaint(request = request)
        }).flowOn(ioDispatcher)
    // endregion

    // region ClassifiedAdFilters
    suspend fun getListingFilters(filterType: String) =
        networkHandler.handleResponse(request = {
            listingClient.getListingFilters(filterType = filterType)
        }).flowOn(ioDispatcher)

    // endregion

    // region FavoriteClassifiedAd
    suspend fun getFavoriteLists() =
        networkHandler.handleResponse(request = {
            listingClient.getFavoriteLists()
        }).flowOn(ioDispatcher)

    suspend fun getFavoriteList(listId: String) =
        networkHandler.handleResponse(request = {
            listingClient.getFavoriteList(listId = listId)
        }).flowOn(ioDispatcher)

    suspend fun getFavoriteListItems(listId: String) =
        networkHandler.handleResponse(request = {
            listingClient.getFavoriteListItems(listId = listId)
        }).flowOn(ioDispatcher)

    suspend fun postFavoriteLists(request: PutFavoriteListsRequest) =
        networkHandler.handleResponse(request = {
            listingClient.postFavoriteLists(request = request)
        }).flowOn(ioDispatcher)

    suspend fun postFavoriteListsByListId(listId: String, request: PostFavoriteListsRequest) =
        networkHandler.handleResponse(request = {
            listingClient.postFavoriteListsByListId(listId = listId, request = request)
        }).flowOn(ioDispatcher)

    suspend fun deleteFavoriteAdvertByListId(listingId: String) =
        networkHandler.handleResponse(request = {
            listingClient.deleteFavoriteAdvertByListId(listingId = listingId)
        }).flowOn(ioDispatcher)

    suspend fun deleteFavoriteList(listId: String) =
        networkHandler.handleResponse(request = {
            listingClient.deleteFavoriteList(listId = listId)
        }).flowOn(ioDispatcher)
    // endregion

    suspend fun putFavoriteListByListId(listId: String, request: PutFavoriteListsRequest) =
        networkHandler.handleResponse(request = {
            listingClient.putFavoriteListByListId(listId = listId, request = request)
        }).flowOn(ioDispatcher)
    // endregion

    // region MotorcycleComparison
    suspend fun getMotorcycleComparison(pageIndex: Int, pageSize: Int) =
        networkHandler.handleResponse(request = {
            listingClient.getMotorcycleComparison(pageIndex = pageIndex, pageSize = pageSize)
        }).flowOn(ioDispatcher)

    suspend fun getMotorcycleComparisonBrands() =
        networkHandler.handleResponse(request = {
            listingClient.getMotorcycleComparisonBrands()
        }).flowOn(ioDispatcher)

    suspend fun getMotorcycleComparisonBrandModels(brandId: String) =
        networkHandler.handleResponse(request = {
            listingClient.getMotorcycleComparisonBrandModels(brandId = brandId)
        }).flowOn(ioDispatcher)

    suspend fun getMotorcycleComparisonSummary(motorcycleModelIds: List<String>) =
        networkHandler.handleResponse(request = {
            listingClient.getMotorcycleComparisonSummary(motorcycleModelIds = motorcycleModelIds)
        }).flowOn(ioDispatcher)

    suspend fun getMotorcycleComparisonCompare(motorcycleIds: List<String>) =
        networkHandler.handleResponse(request = {
            listingClient.getMotorcycleComparisonCompare(motorcycleIds = motorcycleIds)
        }).flowOn(ioDispatcher)

    suspend fun putMotorcycleComparisonVote(
        motorcycleComparisonId: String,
        request: PutEvaluateRequest
    ) =
        networkHandler.handleResponse(request = {
            listingClient.putMotorcycleComparisonVote(
                motorcycleComparisonId = motorcycleComparisonId,
                request = request
            )
        }).flowOn(ioDispatcher)

    // endregion

    // region VehicleType
    suspend fun getVehicleType() =
        networkHandler.handleResponse(request = {
            listingClient.getVehicleType()
        }).flowOn(ioDispatcher)

    // endregion

    //region MetadataForm
    suspend fun getMetadataFormsById(categoryId: String, formType: String) =
        networkHandler.handleResponse(request = {
            listingClient.getMetadataFormsById(categoryId = categoryId, formType = formType)
        }).flowOn(ioDispatcher)

    suspend fun postMetadataFormsById(categoryId: String) =
        networkHandler.handleResponse(request = {
            listingClient.postMetadataFormsById(categoryId = categoryId)
        }).flowOn(ioDispatcher)
    //endregion

    //region PaymenySummary
    suspend fun getPaymentSummary(
        classifiedAdId: String
    ) = networkHandler.handleResponse(request = {
        listingClient.getPaymentSummary(
            classifiedAdId = classifiedAdId
        )
    }).flowOn(ioDispatcher)
    //endregion

    // region Conversations
    suspend fun getConversations(
    ) = networkHandler.handleResponse(request = {
        listingClient.getConversations()
    }).flowOn(ioDispatcher)

    suspend fun postConversationsByClassifiedAdId(
        conversationRequest: ConversationRequest
    ) = networkHandler.handleResponse(request = {
        listingClient.postConversationsByClassifiedAdId(conversationRequest)
    }).flowOn(ioDispatcher)

    suspend fun getConversationByConversationId(
        conversationId: String
    ) = networkHandler.handleResponse(request = {
        listingClient.getConversationByConversationId(conversationId)
    }).flowOn(ioDispatcher)

    suspend fun deleteConversationById(
        conversationId: String
    ) = networkHandler.handleResponse(request = {
        listingClient.deleteConversationById(conversationId)
    }).flowOn(ioDispatcher)

    suspend fun getConversationMessagesById(
        conversationId: String,
        pageIndex: Int,
        pageSize: Int
    ) = networkHandler.handleResponse(request = {
        listingClient.getConversationMessagesById(
            conversationId = conversationId,
            pageIndex = pageIndex,
            pageSize = pageSize
        )
    }).flowOn(ioDispatcher)

    suspend fun postConversationSendMessageById(
        conversationId: String,
        conversationMessageRequest: ConversationMessageRequest
    ) = networkHandler.handleResponse(request = {
        listingClient.postConversationSendMessageById(
            conversationId = conversationId,
            conversationMessageRequest = conversationMessageRequest
        )
    }).flowOn(ioDispatcher)


    suspend fun putConversationComplainById(
        conversationId: String,
        conversationComplainRequest: ConversationComplainRequest
    ) = networkHandler.handleResponse(request = {
        listingClient.putConversationComplainById(
            conversationId = conversationId,
            conversationComplainRequest = conversationComplainRequest
        )
    }).flowOn(ioDispatcher)

    //endregion


}
