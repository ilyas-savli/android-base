package com.nyth.app.core.network.service

import com.nyth.app.core.model.annotation.Authenticated
import com.nyth.app.core.model.local.enums.FormType
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
import com.nyth.app.core.model.remote.response.AcceleratePackageResponse
import com.nyth.app.core.model.remote.response.AcceleratePriceResponse
import com.nyth.app.core.model.remote.response.AccountListingResponse
import com.nyth.app.core.model.remote.response.AdvertDetailResponse
import com.nyth.app.core.model.remote.response.BaseResponse
import com.nyth.app.core.model.remote.response.BrandModelPopularResponse
import com.nyth.app.core.model.remote.response.BrandModelsResponse
import com.nyth.app.core.model.remote.response.BrandsResponse
import com.nyth.app.core.model.remote.response.CategoriesPopularResponse
import com.nyth.app.core.model.remote.response.CategoryResponse
import com.nyth.app.core.model.remote.response.CityResponse
import com.nyth.app.core.model.remote.response.ComparisonCompareResponse
import com.nyth.app.core.model.remote.response.ComparisonSummaryResponse
import com.nyth.app.core.model.remote.response.ConversationComplainResponse
import com.nyth.app.core.model.remote.response.ConversationMessageResponse
import com.nyth.app.core.model.remote.response.ConversationPostResponse
import com.nyth.app.core.model.remote.response.ConversationResponse
import com.nyth.app.core.model.remote.response.ConversationSendMessageResponse
import com.nyth.app.core.model.remote.response.DistrictResponse
import com.nyth.app.core.model.remote.response.FavoriteListItemResponse
import com.nyth.app.core.model.remote.response.FavoriteListsResponse
import com.nyth.app.core.model.remote.response.FeaturedBrandResponse
import com.nyth.app.core.model.remote.response.ListingDraftResponse
import com.nyth.app.core.model.remote.response.ListingFilterResponse
import com.nyth.app.core.model.remote.response.ListingFiltersResponse
import com.nyth.app.core.model.remote.response.ListingMediaResponse
import com.nyth.app.core.model.remote.response.ListingPreviewResponse
import com.nyth.app.core.model.remote.response.ListingShowCaseResponse
import com.nyth.app.core.model.remote.response.MetadataFormResponse
import com.nyth.app.core.model.remote.response.MotorcycleComparisonResponse
import com.nyth.app.core.model.remote.response.PaymentSummaryResponse
import com.nyth.app.core.model.remote.response.PostFavoriteListResponse
import com.nyth.app.core.model.remote.response.PostListingComplaintResponse
import com.nyth.app.core.model.remote.response.RelatedLinksResponse
import com.nyth.app.core.model.remote.response.SearchAdvertResponse
import com.nyth.app.core.model.remote.response.SimilarListingsResponse
import com.nyth.app.core.model.remote.response.SubCategoryResponse
import com.nyth.app.core.model.remote.response.VehicleTypeResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ListingService {
    object Endpoint {
        /**
         * Paths
         */
        const val brandId = "brandId"
        const val brandModelId = "brandModelId"
        const val acceleratePriceId = "acceleratePriceId"
        const val categoryId = "categoryId"
        const val categoryType = "categoryType"
        const val cityId = "cityId"
        const val listingId = "listingId"
        const val mediaId = "mediaId"
        const val formType = "formType"
        const val searchTerm = "searchTerm"
        const val classifiedAdNumber = "classifiedAdNumber"
        const val classifiedAdId = "classifiedAdId"
        const val smilarClassifiedAdId = "ClassifiedAdId"
        const val accountId = "accountId"
        const val acceleratePackagePriceId = "acceleratePackagePriceId"
        const val vehicleTypeId = "vehicleTypeId"
        const val pageIndex = "pageIndex"
        const val pageSize = "pageSize"
        const val popular = "popular"
        const val listId = "listId"
        const val filterType = "filterType"
        const val motorcycleIds = "motorcycleIds"
        const val motorcycleModelIds = "motorcycleModelIds"
        const val motorcycleComparisonId = "motorcycleComparisonId"

        /**
         * Queries
         */
        const val isActive = "isActive"
        const val orderBy = "orderBy"
        const val conversationId = "conversationId"
        const val messages = "messages"
        const val send = "send"
        const val complain = "complain"

        object AcceleratePackagePrice {
            private const val mainPath = "accelerate_price"
            const val getAcceleratePrice = "$mainPath/{$categoryId}"
            const val getAcceleratePackageList = "${mainPath}/listing/{$classifiedAdId}"
            const val putAcceleratePrice = "$mainPath/{$acceleratePriceId}"
        }

        object Brand {
            private const val mainPath = "brands"
            const val getBrands = "${mainPath}/{$categoryId}"
            const val getBrandFeatured = "${mainPath}/featured/{$categoryId}"
            const val getBrandDetail = "${mainPath}/{$brandId}"
            const val postBrand = mainPath
            const val putBrand = "${mainPath}/{$brandId}"
            const val deleteBrand = "${mainPath}/{$brandId}"
            const val putBrandFeatured = "${mainPath}/featured/{$brandId}"
        }

        object BrandModel {
            private const val mainPath = "brand_models"
            const val getBrandModels = "$mainPath/{$brandId}"
            const val postBrandModel = "$mainPath/{$brandId}"
            const val getBrandModelDetails = "$mainPath/{$brandModelId}/details"
            const val putBrandModel = "$mainPath/{$brandModelId}"
            const val getBrandModelsPopular = "$mainPath/$popular"
        }

        object Category {
            private const val mainPath = "categories"
            const val getCategories = mainPath
            const val postCategories = mainPath
            const val getCategoryById = "${mainPath}/{$categoryId}"
            const val getCategoriesPopular = "${mainPath}/$popular"
        }

        object City {
            private const val mainPath = "city"
            const val getCities = mainPath
            const val getTowns = "${mainPath}/{$cityId}/towns"
        }

        object ClassifiedAd {
            private const val mainPath = "listing"
            const val postListing = mainPath
            const val putListing = "$mainPath/{$listingId}"
            const val deleteListing = "$mainPath/{$listingId}"
            const val listingChangeStatus = "$mainPath/{$listingId}/change_status"
            const val postListingFilter = "$mainPath/filter"
            const val getListingDraft = "$mainPath/latest_draft"
            const val deleteListingDraft = "$mainPath/latest_draft"
            const val putListingComplete = "$mainPath/{$listingId}/complete"
            const val search = "$mainPath/search"
            const val getListingDetail = "$mainPath/{$classifiedAdNumber}/detail"
            const val getListingPreview = "$mainPath/{$listingId}/preview"
            const val getListingShowCase = "$mainPath/showcase"
            const val postListingMedia = "$mainPath/{$listingId}/media"
            const val putListingMedia = "$mainPath/{$listingId}/media/{$mediaId}"
            const val deleteListingMedia = "$mainPath/{$listingId}/media/{$mediaId}"
            const val getListingSimilarListings = "$mainPath/similar-listings"
            const val getListingRelatedLinks = "$mainPath/related-links"
            const val postAcceleratePackage = "$mainPath/accelerate/{$classifiedAdId}"
            const val putAcceleratePackage = "$mainPath/accelerate/{$classifiedAdId}"
            const val deleteAcceleratePackage =
                "$mainPath/accelerate/{$classifiedAdId}/{$acceleratePackagePriceId}"
            const val postListingComplaintt = "$mainPath/complaint"
            const val accountListings = "$mainPath/account-listings"
            const val accountListingsByAccountID = "$mainPath/account-listings/{$accountId}"
        }

        object FavoriteClassifiedAd {
            private const val mainPath = "favorite_lists"
            const val getFavoriteLists = mainPath
            const val getFavoriteList =
                "$mainPath/{$listId}"
            const val getFavoriteListItems = "$mainPath/{$listId}/listings"
            const val postFavoriteLists = mainPath
            const val postFavoriteListsByListId = "$mainPath/{$listId}"
            const val putFavoriteListByListId = "$mainPath/{$listId}"
            const val deleteFavoriteList = "$mainPath/{$listId}"
            const val deleteFavoriteByListingId = "$mainPath/listing/{$listingId}"
        }

        object MetadataForm {
            private const val mainPath = "metadata_forms"
            const val getMetadataFormsById = "$mainPath/{$categoryId}"
            const val postMetadataFormsById = "$mainPath/{$categoryId}"
        }

        object ClassifiedAdFilters {
            private const val mainPath = "listing_filters"
            const val getListingFilters = mainPath
        }

        object MotorcycleComparison {
            private const val mainPath = "motorcycle_comparison"
            const val getMotorcycleComparison = mainPath
            const val getMotorcycleComparisonBrands = "$mainPath/brands"
            const val getMotorcycleComparisonBrandModels = "$mainPath/brand-models/{$brandId}"
            const val getMotorcycleComparisonSummary = "$mainPath/summary"
            const val getMotorcycleComparisonCompare = "$mainPath/compare"
            const val putMotorcycleComparisonVote = "$mainPath/vote/{$motorcycleComparisonId}"
        }

        object VehicleType {
            private const val mainPath = "vehicle_type"
            const val getVehicleType = mainPath
        }

        object PaymentSummary {
            private const val mainPath = "payment"
            const val getPaymentSummary = "${mainPath}/summary"
        }

        object Conversation {
            private const val mainPath = "conversations"
            const val getConversations = mainPath
            const val postConversationsByClassifiedAdId = mainPath
            const val getConversationByConversationId = "${mainPath}/{$conversationId}"
            const val deleteConversationById = "${mainPath}/{$conversationId}"
            const val getConversationMessagesById = "${mainPath}/{$conversationId}/$messages"
            const val postConversationSendMessageById = "${mainPath}/{$conversationId}/$send"
            const val putConversationComplainById = "${mainPath}/{$conversationId}/$complain"

        }
    }

    // region AcceleratePackagePrice
    @GET(Endpoint.AcceleratePackagePrice.getAcceleratePrice)
    suspend fun getAcceleratePrice(@Path(Endpoint.categoryId) categoryId: String): Response<BaseResponse<List<AcceleratePriceResponse>>>

    @Authenticated
    @GET(Endpoint.AcceleratePackagePrice.getAcceleratePackageList)
    suspend fun getAcceleratePackageList(
        @Path(Endpoint.classifiedAdId) classifiedAdId: String
    ): Response<BaseResponse<List<AcceleratePackageResponse>>>

    @PUT(Endpoint.AcceleratePackagePrice.putAcceleratePrice)
    suspend fun putAcceleratePrice(@Path(Endpoint.acceleratePriceId) acceleratePriceId: String): Response<Unit>

    // endregion

    // region Brand
    @GET(Endpoint.Brand.getBrands)
    suspend fun getBrands(@Path(Endpoint.categoryId) categoryId: String): Response<BaseResponse<List<BrandsResponse>>>

    @GET(Endpoint.Brand.getBrandFeatured)
    suspend fun getBrandFeatured(@Path(Endpoint.categoryId) categoryId: String): Response<BaseResponse<List<FeaturedBrandResponse>>>

    @GET(Endpoint.Brand.getBrandDetail)
    suspend fun getBrandDetail(@Path(Endpoint.brandId) brandId: String): Response<Unit>

    @POST(Endpoint.Brand.postBrand)
    suspend fun postBrand(): Response<Unit>

    @PUT(Endpoint.Brand.putBrand)
    suspend fun putBrand(@Path(Endpoint.brandId) brandId: String): Response<Unit>

    @Authenticated
    @DELETE(Endpoint.Brand.deleteBrand)
    suspend fun deleteBrand(@Path(Endpoint.brandId) brandId: String): Response<Unit>

    @PUT(Endpoint.Brand.putBrandFeatured)
    suspend fun putBrandFeatured(@Path(Endpoint.brandId) brandId: String): Response<Unit>

    // endregion

    // region BrandModel
    @GET(Endpoint.BrandModel.getBrandModels)
    suspend fun getBrandModels(@Path(Endpoint.brandId) brandId: String): Response<BaseResponse<List<BrandModelsResponse>>>

    @POST(Endpoint.BrandModel.postBrandModel)
    suspend fun postBrandModel(@Path(Endpoint.brandId) brandId: String): Response<Unit>

    @GET(Endpoint.BrandModel.getBrandModelDetails)
    suspend fun getBrandModelDetails(@Path(Endpoint.brandModelId) brandModelId: String): Response<Unit>

    @PUT(Endpoint.BrandModel.putBrandModel)
    suspend fun putBrandModel(@Path(Endpoint.brandModelId) brandModelId: String): Response<Unit>

    @GET(Endpoint.BrandModel.getBrandModelsPopular)
    suspend fun getBrandModelsPopular(
        @Query(Endpoint.categoryType) categoryType: String, @Query(Endpoint.brandId) brandId: String
    ): Response<BaseResponse<List<BrandModelPopularResponse>>>

    // endregion

    // region Category
    @GET(Endpoint.Category.getCategories)
    suspend fun getCategories(): Response<BaseResponse<List<CategoryResponse>>>

    @POST(Endpoint.Category.postCategories)
    suspend fun postCategories(): Response<Unit>

    @GET(Endpoint.Category.getCategoryById)
    suspend fun getCategoriesById(
        @Path(Endpoint.categoryId) categoryId: String
    ): Response<BaseResponse<List<SubCategoryResponse>>>

    @GET(Endpoint.Category.getCategoriesPopular)
    suspend fun getCategoriesPopular(
        @Query(Endpoint.categoryType) categoryType: String
    ): Response<BaseResponse<List<CategoriesPopularResponse>>>
    // endregion

    // region City

    @GET(Endpoint.City.getCities)
    suspend fun getCities(): Response<BaseResponse<List<CityResponse>>>

    @GET(Endpoint.City.getTowns)
    suspend fun getTowns(@Path(Endpoint.cityId) cityId: String): Response<BaseResponse<List<DistrictResponse>>>

    // endregion

    // region ClassifiedAd

    @Authenticated
    @POST(Endpoint.ClassifiedAd.postListing)
    suspend fun postListing(@Body request: ListingAddRequest): Response<Unit>

    @Authenticated
    @PUT(Endpoint.ClassifiedAd.putListing)
    suspend fun putListing(
        @Path(Endpoint.listingId) listingId: String,
        @Body request: ListingAddRequest
    ): Response<Unit>

    @Authenticated
    @GET(Endpoint.ClassifiedAd.getListingDraft)
    suspend fun getListingDraft(): Response<BaseResponse<ListingDraftResponse>>

    @Authenticated
    @PUT(Endpoint.ClassifiedAd.putListingComplete)
    suspend fun putListingComplete(
        @Path(Endpoint.listingId) listingId: String
    ): Response<Unit>

    @Authenticated
    @DELETE(Endpoint.ClassifiedAd.deleteListingDraft)
    suspend fun deleteListingDraft(): Response<Unit>

    @Authenticated
    @DELETE(Endpoint.ClassifiedAd.deleteListing)
    suspend fun deleteListing(
        @Path(Endpoint.listingId) classifiedAdId: String
    ): Response<Unit>

    @Authenticated
    @PUT(Endpoint.ClassifiedAd.listingChangeStatus)
    suspend fun changeStatus(
        @Path(Endpoint.listingId) classifiedAdId: String,
        @Body request: PutChangeListingStatusRequest
    ): Response<Unit>

    @GET(Endpoint.ClassifiedAd.search)
    suspend fun getListingSearch(
        @Query(Endpoint.searchTerm) searchTerm: String
    ): Response<BaseResponse<List<SearchAdvertResponse>>>

    @Authenticated
    @GET(Endpoint.ClassifiedAd.getListingDetail)
    suspend fun getListingDetail(
        @Path(Endpoint.classifiedAdNumber) classifiedAdNumber: Int
    ): Response<BaseResponse<AdvertDetailResponse>>

    @Authenticated
    @GET(Endpoint.ClassifiedAd.getListingPreview)
    suspend fun getListingPreview(
        @Path(Endpoint.listingId) listingId: String
    ): Response<BaseResponse<ListingPreviewResponse>>
    @Authenticated
    @GET(Endpoint.ClassifiedAd.getListingPreview)
    suspend fun getListingWithClassifiedAdId(
        @Path(Endpoint.listingId) listingId: String
    ): Response<BaseResponse<AdvertDetailResponse>>

    @GET(Endpoint.ClassifiedAd.getListingSimilarListings)
    suspend fun getListingSimilarListings(
        @Query(Endpoint.smilarClassifiedAdId) classifiedAdId: String,
        @Query(Endpoint.brandId) brandId: String,
        @Query(Endpoint.brandModelId) brandModelId: String,
        @Query(Endpoint.vehicleTypeId) vehicleTypeId: String
    ): Response<BaseResponse<List<SimilarListingsResponse>>>

    @Authenticated
    @GET(Endpoint.ClassifiedAd.accountListings)
    suspend fun getAccountListings(
        @Query(Endpoint.isActive) isActive: Boolean?,
        @Query(Endpoint.orderBy) orderBy: String?,
        @Query(Endpoint.categoryType) categoryType: String?,
        @Query(Endpoint.pageIndex) pageIndex: Int?,
        @Query(Endpoint.pageSize) pageSize: Int?
    ): Response<BaseResponse<List<AccountListingResponse>>>

    @GET(Endpoint.ClassifiedAd.accountListingsByAccountID)
    suspend fun getAccountListingsByAccountID(
        @Path(Endpoint.accountId) accountId: String?,
        @Query(Endpoint.isActive) isActive: Boolean?,
        @Query(Endpoint.orderBy) orderBy: String?,
        @Query(Endpoint.categoryType) categoryType: String?,
        @Query(Endpoint.pageIndex) pageIndex: Int?,
        @Query(Endpoint.pageSize) pageSize: Int?
    ): Response<BaseResponse<List<AccountListingResponse>>>

    @Authenticated
    @POST(Endpoint.ClassifiedAd.postAcceleratePackage)
    suspend fun postAcceleratePackageList(
        @Path(Endpoint.classifiedAdId) classifiedAdId: String,
        @Body request: PostAcceleratePackageRequest
    ): Response<Unit>

    @Authenticated
    @PUT(Endpoint.ClassifiedAd.putAcceleratePackage)
    suspend fun putAcceleratePackageList(
        @Path(Endpoint.classifiedAdId) classifiedAdId: String,
        @Body request: PutAcceleratePackageRequest
    ): Response<Unit>

    @Authenticated
    @DELETE(Endpoint.ClassifiedAd.deleteAcceleratePackage)
    suspend fun deleteAcceleratePackageList(
        @Path(Endpoint.classifiedAdId) classifiedAdId: String,
        @Path(Endpoint.acceleratePackagePriceId) acceleratePackagePriceId: String
    ): Response<Unit>

    @Authenticated
    @POST(Endpoint.ClassifiedAd.postListingFilter)
    suspend fun postListingFilter(@Body request: ListingFilterRequest): Response<BaseResponse<List<ListingFilterResponse>>>

    @GET(Endpoint.ClassifiedAd.getListingShowCase)
    suspend fun getListingShowCase(
        @Query(Endpoint.vehicleTypeId) vehicleTypeId: String
    ): Response<BaseResponse<List<ListingShowCaseResponse>>>

    @Authenticated
    @Multipart
    @POST(Endpoint.ClassifiedAd.postListingMedia)
    suspend fun postListingMedia(
        @Path(Endpoint.listingId) listingId: String,
        @Part("IsCover") isCover: RequestBody,
        @Part("Order") order: RequestBody,
        @Part file: MultipartBody.Part,
        @Part("MediaType") mediaType: RequestBody
    ): Response<BaseResponse<ListingMediaResponse>>

    @Authenticated
    @PUT(Endpoint.ClassifiedAd.putListingMedia)
    suspend fun putListingMedia(
        @Path(Endpoint.listingId) listingId: String,
        @Path(Endpoint.mediaId) mediaId: String,
        @Body request: ListingMediaPutRequest
    ): Response<Unit>

    @Authenticated
    @DELETE(Endpoint.ClassifiedAd.deleteListingMedia)
    suspend fun deleteListingMedia(
        @Path(Endpoint.listingId) listingId: String,
        @Path(Endpoint.mediaId) mediaId: String
    ): Response<Unit>

    @GET(Endpoint.ClassifiedAd.getListingRelatedLinks)
    suspend fun getListingRelatedLinks(
        @Query(Endpoint.categoryId) categoryId: String,
        @Query(Endpoint.brandId) brandId: String,
        @Query(Endpoint.brandModelId) brandModelId: String,
    ): Response<BaseResponse<RelatedLinksResponse>>

    @Authenticated
    @POST(Endpoint.ClassifiedAd.postListingComplaintt)
    suspend fun postListingComplaint(
        @Body request: PostListingComplaintRequest
    ): Response<BaseResponse<PostListingComplaintResponse>>

    // endregion

    // region FavoriteClassifiedAd
    @Authenticated
    @GET(Endpoint.FavoriteClassifiedAd.getFavoriteLists)
    suspend fun getFavoriteLists(): Response<BaseResponse<List<FavoriteListsResponse>>>

    @Authenticated
    @GET(Endpoint.FavoriteClassifiedAd.getFavoriteList)
    suspend fun getFavoriteList(
        @Path(Endpoint.listId)
        listId: String
    ): Response<BaseResponse<FavoriteListsResponse>>

    @Authenticated
    @GET(Endpoint.FavoriteClassifiedAd.getFavoriteListItems)
    suspend fun getFavoriteListItems(
        @Path(Endpoint.listId) listId: String
    ): Response<BaseResponse<List<FavoriteListItemResponse>>>

    @Authenticated
    @POST(Endpoint.FavoriteClassifiedAd.postFavoriteLists)
    suspend fun postFavoriteLists(
        @Body request: PutFavoriteListsRequest
    ): Response<BaseResponse<PostFavoriteListResponse>>

    @Authenticated
    @POST(Endpoint.FavoriteClassifiedAd.postFavoriteListsByListId)
    suspend fun postFavoriteListsByListId(
        @Path(Endpoint.listId) listId: String, @Body request: PostFavoriteListsRequest
    ): Response<BaseResponse<PostFavoriteListResponse>>

    @Authenticated
    @DELETE(Endpoint.FavoriteClassifiedAd.deleteFavoriteByListingId)
    suspend fun deleteFavoriteAdvertByListId(
        @Path(Endpoint.listingId) listingId: String
    ): Response<Unit>

    @Authenticated
    @DELETE(Endpoint.FavoriteClassifiedAd.deleteFavoriteList)
    suspend fun deleteFavoriteList(
        @Path(Endpoint.listId) listId: String
    ): Response<Unit>

    @Authenticated
    @PUT(Endpoint.FavoriteClassifiedAd.putFavoriteListByListId)
    suspend fun putFavoriteListByListId(
        @Path(Endpoint.listId) listId: String, @Body request: PutFavoriteListsRequest
    ): Response<Unit>
    // endregion

    // region MetadataForm
    @GET(Endpoint.MetadataForm.getMetadataFormsById)
    suspend fun getMetadataFormsById(
        @Path(Endpoint.categoryId) categoryId: String,
        @Query(Endpoint.formType) formType: String = FormType.Detail.type
    ): Response<BaseResponse<List<MetadataFormResponse>>>

    @Authenticated
    @POST(Endpoint.MetadataForm.postMetadataFormsById)
    suspend fun postMetadataFormsById(@Path(Endpoint.categoryId) categoryId: String): Response<Unit>
    // endregion

    // region ClassifiedAdFilters
    @Authenticated
    @GET(Endpoint.ClassifiedAdFilters.getListingFilters)
    suspend fun getListingFilters(
        @Query(Endpoint.filterType) filterType: String
    ): Response<BaseResponse<List<ListingFiltersResponse>>>

    // endregion

    // region MotorcycleComparison
    @Authenticated
    @GET(Endpoint.MotorcycleComparison.getMotorcycleComparison)
    suspend fun getMotorcycleComparison(
        @Query(Endpoint.pageIndex) pageIndex: Int,
        @Query(Endpoint.pageSize) pageSize: Int,
    ): Response<BaseResponse<List<MotorcycleComparisonResponse>>>

    @Authenticated
    @GET(Endpoint.MotorcycleComparison.getMotorcycleComparisonBrands)
    suspend fun getMotorcycleComparisonBrands(): Response<BaseResponse<List<BrandsResponse>>>

    @Authenticated
    @GET(Endpoint.MotorcycleComparison.getMotorcycleComparisonBrandModels)
    suspend fun getMotorcycleComparisonBrandModels(
        @Path(Endpoint.brandId) brandId: String
    ): Response<BaseResponse<List<BrandModelsResponse>>>

    @Authenticated
    @GET(Endpoint.MotorcycleComparison.getMotorcycleComparisonSummary)
    suspend fun getMotorcycleComparisonSummary(
        @Query(Endpoint.motorcycleModelIds) motorcycleModelIds: List<String>,
    ): Response<BaseResponse<ComparisonSummaryResponse>>

    @Authenticated
    @GET(Endpoint.MotorcycleComparison.getMotorcycleComparisonCompare)
    suspend fun getMotorcycleComparisonCompare(
        @Query(Endpoint.motorcycleIds) motorcycleIds: List<String>,
    ): Response<BaseResponse<ComparisonCompareResponse>>

    @Authenticated
    @PUT(Endpoint.MotorcycleComparison.putMotorcycleComparisonVote)
    suspend fun putMotorcycleComparisonVote(
        @Path(Endpoint.motorcycleComparisonId) motorcycleComparisonId: String,
        @Body request: PutEvaluateRequest
    ): Response<Unit>
    // endregion

    // region VehicleType
    @GET(Endpoint.VehicleType.getVehicleType)
    suspend fun getVehicleType(): Response<BaseResponse<List<VehicleTypeResponse>>>
    // endregion

    // region PaymentSummary
    @Authenticated
    @GET(Endpoint.PaymentSummary.getPaymentSummary)
    suspend fun getPaymentSummary(
        @Query(Endpoint.classifiedAdId) classifiedAdId: String
    ): Response<BaseResponse<PaymentSummaryResponse>>
    // endregion

    // region Conversations
    @Authenticated
    @GET(Endpoint.Conversation.getConversations)
    suspend fun getConversations(): Response<BaseResponse<List<ConversationResponse>>>

    @Authenticated
    @POST(Endpoint.Conversation.postConversationsByClassifiedAdId)
    suspend fun postConversationsByClassifiedAdId(
        @Body conversationRequest: ConversationRequest
    ): Response<BaseResponse<ConversationPostResponse>>

    @Authenticated
    @GET(Endpoint.Conversation.getConversationByConversationId)
    suspend fun getConversationByConversationId(
        @Path(Endpoint.conversationId) conversationId: String,
    ): Response<BaseResponse<ConversationResponse>>

    @Authenticated
    @DELETE(Endpoint.Conversation.deleteConversationById)
    suspend fun deleteConversationById(
        @Path(Endpoint.conversationId) conversationId: String,
    ): Response<BaseResponse<ConversationResponse>>

    @Authenticated
    @GET(Endpoint.Conversation.getConversationMessagesById)
    suspend fun getConversationMessagesById(
        @Path(Endpoint.conversationId) conversationId: String,
        @Query(Endpoint.pageIndex) pageIndex: Int,
        @Query(Endpoint.pageSize) pageSize: Int,
    ): Response<BaseResponse<List<ConversationMessageResponse>>>

    @Authenticated
    @POST(Endpoint.Conversation.postConversationSendMessageById)
    suspend fun postConversationSendMessageById(
        @Path(Endpoint.conversationId) conversationId: String,
        @Body conversationMessageRequest: ConversationMessageRequest
    ): Response<BaseResponse<ConversationSendMessageResponse>>

    @Authenticated
    @PUT(Endpoint.Conversation.putConversationComplainById)
    suspend fun putConversationComplainById(
        @Path(Endpoint.conversationId) conversationId: String,
        @Body conversationComplainRequest: ConversationComplainRequest
    ): Response<BaseResponse<ConversationComplainResponse>>

    // endregion


}
