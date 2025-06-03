package com.nyth.app.core.network.service

import com.nyth.app.core.model.annotation.Authenticated
import com.nyth.app.core.model.local.enums.GrantType
import com.nyth.app.core.model.remote.request.ConfirmAccountRequest
import com.nyth.app.core.model.remote.request.ConfirmEmailRequest
import com.nyth.app.core.model.remote.request.ConfirmPhoneNumberRequest
import com.nyth.app.core.model.remote.request.ExternalRegisterRequest
import com.nyth.app.core.model.remote.request.PutChangeNameRequest
import com.nyth.app.core.model.remote.request.RegisterRequest
import com.nyth.app.core.model.remote.request.RequestCodeRequest
import com.nyth.app.core.model.remote.request.RequestEmailCodeRequest
import com.nyth.app.core.model.remote.request.RequestPhoneCodeRequest
import com.nyth.app.core.model.remote.response.AccountResponse
import com.nyth.app.core.model.remote.response.BaseResponse
import com.nyth.app.core.model.remote.response.ListingMediaResponse
import com.nyth.app.core.model.remote.response.PackageCheckResponse
import com.nyth.app.core.model.remote.response.RefreshTokenResponse
import com.nyth.app.core.model.remote.response.RegisterResponse
import com.nyth.app.core.model.remote.response.RequestCodeResponse
import com.nyth.app.core.model.remote.response.RequestPhoneCodeResponse
import com.nyth.app.core.model.remote.response.RolesResponse
import com.nyth.app.core.model.remote.response.TokenResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface IdentityService {
    object Endpoint {
        /**
         * Paths
         */
        const val accountId = "accountId"

        /**
         * Fields
         */
        const val username = "username"
        const val password = "password"
        const val grantType = "grant_type"
        const val clientId = "client_id"
        const val clientSecret = "client_secret"
        const val provider = "provider"
        const val scope = "scope"
        const val identityToken = "identity_token"
        const val classifiedAdId = "classifiedAdId"
        const val deleteCoverImage = "delete_profile_cover_image"

        object Account {
            private const val mainPath = "accounts"
            const val accounts = mainPath
            const val accountById = "$mainPath/{$accountId}"
            const val accountDeleteCoverImage = "$mainPath/profile/$deleteCoverImage"
            const val profileMe = "$mainPath/profile/me"
            const val accountProfileById = "$mainPath/profile/{$accountId}"
            const val putChangeName = "$mainPath/profile/{$accountId}/change_name"
            const val putChangeProfileImage = "$mainPath/profile/change_profile_image"
            const val putChangeProfileCoverImage = "$mainPath/profile/change_profile_cover_image"
            const val accountLockById = "$mainPath/lock/{$accountId}"
            const val accountUnlockById = "$mainPath/unlock/{$accountId}"
            const val requestPhoneCode = "$mainPath/request_phone_code"
            const val confirmPhoneNumber = "$mainPath/confirm_phone_number"
            const val requestEmailCode = "$mainPath/profile/change_email_request"
            const val confirmEmail = "$mainPath/profile/change_email_confirm"
        }

        object Authentication {
            private const val mainPath = "authentication"
            const val request_code = "$mainPath/request_code"
            const val register = "$mainPath/register"
            const val confirmAccount = "$mainPath/confirm_account"
            const val externalRegister = "$mainPath/external_register"
        }

        object Package {
            private const val mainPath = "packages"
            const val packages = mainPath
            const val packagesCheck = "$mainPath/check"
        }

        object Role {
            private const val mainPath = "roles"
            const val roles = mainPath
        }

        object Connect {
            private const val mainPath = "connect"
            const val token = "$mainPath/token"
        }
    }

    // region Account
    @GET(Endpoint.Account.accounts)
    suspend fun getAccounts(): Response<Unit>

    @Authenticated
    @GET(Endpoint.Account.accountById)
    suspend fun getAccountById(@Path(Endpoint.accountId) accountId: String): Response<BaseResponse<AccountResponse>>

    @Authenticated
    @GET(Endpoint.Account.profileMe)
    suspend fun getProfile(): Response<BaseResponse<AccountResponse>>

    @Authenticated
    @GET(Endpoint.Account.accountById)
    suspend fun getProfileById(@Path(Endpoint.accountId) accountId: String): Response<BaseResponse<AccountResponse>>

    @Authenticated
    @PUT(Endpoint.Account.putChangeName)
    suspend fun putChangeName(
        @Path(Endpoint.accountId) accountId: String,
        @Body request: PutChangeNameRequest
    ): Response<Unit>

    @Authenticated
    @Multipart
    @PUT(Endpoint.Account.putChangeProfileImage)
    suspend fun putProfileImage(
        @Part file: MultipartBody.Part
    ): Response<Unit>

    @Authenticated
    @Multipart
    @PUT(Endpoint.Account.putChangeProfileCoverImage)
    suspend fun putProfileCoverImage(
        @Part file: MultipartBody.Part
    ): Response<Unit>

    @Authenticated
    @DELETE(Endpoint.Account.accountById)
    suspend fun deleteAccountById(@Path(Endpoint.accountId) accountId: String): Response<Unit>

    @Authenticated
    @DELETE(Endpoint.Account.accountDeleteCoverImage)
    suspend fun deleteAccountCoverImage(): Response<Unit>

    @Authenticated
    @GET(Endpoint.Account.accountProfileById)
    suspend fun getAccountProfileById(
        @Path(Endpoint.accountId) accountId: String,
        @Query(Endpoint.classifiedAdId) classifiedAdId: String,
    ): Response<BaseResponse<AccountResponse>>

    @Authenticated
    @PUT(Endpoint.Account.accountProfileById)
    suspend fun putAccountProfileById(@Path(Endpoint.accountId) accountId: String): Response<Unit>

    @POST(Endpoint.Account.accountLockById)
    suspend fun accountLockById(@Path(Endpoint.accountId) accountId: String): Response<Unit>

    @POST(Endpoint.Account.accountUnlockById)
    suspend fun accountUnlockById(@Path(Endpoint.accountId) accountId: String): Response<Unit>

    @Authenticated
    @POST(Endpoint.Account.requestPhoneCode)
    suspend fun requestPhoneCode(@Body request: RequestPhoneCodeRequest): Response<BaseResponse<RequestPhoneCodeResponse>>

    @Authenticated
    @POST(Endpoint.Account.confirmPhoneNumber)
    suspend fun confirmPhoneNumber(@Body request: ConfirmPhoneNumberRequest): Response<Unit>

    @Authenticated
    @POST(Endpoint.Account.requestEmailCode)
    suspend fun requestEmailCode(@Body request: RequestEmailCodeRequest): Response<Unit>

    @Authenticated
    @POST(Endpoint.Account.confirmEmail)
    suspend fun confirmEmail(@Body request: ConfirmEmailRequest): Response<Unit>


    // endregion

    // region Authentication

    @POST(Endpoint.Authentication.request_code)
    suspend fun requestCode(@Body request: RequestCodeRequest): Response<BaseResponse<RequestCodeResponse>>

    @POST(Endpoint.Authentication.register)
    suspend fun register(@Body request: RegisterRequest): Response<BaseResponse<RegisterResponse>>

    @POST(Endpoint.Authentication.confirmAccount)
    suspend fun confirmAccount(@Body request: ConfirmAccountRequest): Response<Unit>

    @POST(Endpoint.Authentication.externalRegister)
    suspend fun externalRegister(@Body request: ExternalRegisterRequest): Response<Unit>

    // endregion

    // region Packages

    @Authenticated
    @GET(Endpoint.Package.packagesCheck)
    suspend fun getPackagesCheck(
        @Query("packageActionType") packageActionType: String?
    ): Response<BaseResponse<PackageCheckResponse>>

    // endregion

    // region Role
    @GET(Endpoint.Role.roles)
    suspend fun getRoles(): Response<BaseResponse<RolesResponse>>

    // endregion

    // region Connect
    @FormUrlEncoded
    @POST(Endpoint.Connect.token)
    suspend fun token(
        @Field(Endpoint.username) username: String,
        @Field(Endpoint.password) password: String,
        @Field(Endpoint.grantType) grantType: String = GrantType.Password.type,
        @Field(Endpoint.clientId) clientId: String,
        @Field(Endpoint.clientSecret) clientSecret: String
    ): Response<TokenResponse>

    @FormUrlEncoded
    @POST(Endpoint.Connect.token)
    suspend fun tokenExternalLogin(
        @Field(Endpoint.provider) provider: String,
        @Field(Endpoint.scope) scope: String,
        @Field(Endpoint.identityToken) identityToken: String,
        @Field(Endpoint.grantType) grantType: String,
        @Field(Endpoint.clientId) clientId: String,
        @Field(Endpoint.clientSecret) clientSecret: String,
    ): Response<TokenResponse>

    @FormUrlEncoded
    @POST(Endpoint.Connect.token)
    suspend fun refreshToken(
        @Field(Endpoint.password) password: String,
        @Field(Endpoint.grantType) grantType: String = GrantType.RefreshToken.type,
        @Field(Endpoint.clientId) clientId: String,
        @Field(Endpoint.clientSecret) clientSecret: String
    ): Response<RefreshTokenResponse>

    // endregion
}
