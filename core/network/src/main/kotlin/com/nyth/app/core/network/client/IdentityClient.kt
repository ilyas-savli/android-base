package com.nyth.app.core.network.client

import com.nyth.app.core.model.remote.request.ConfirmAccountRequest
import com.nyth.app.core.model.remote.request.ConfirmEmailRequest
import com.nyth.app.core.model.remote.request.ConfirmPhoneNumberRequest
import com.nyth.app.core.model.remote.request.ExternalRegisterRequest
import com.nyth.app.core.model.remote.request.PutChangeNameRequest
import com.nyth.app.core.model.remote.request.RegisterRequest
import com.nyth.app.core.model.remote.request.RequestCodeRequest
import com.nyth.app.core.model.remote.request.RequestEmailCodeRequest
import com.nyth.app.core.model.remote.request.RequestPhoneCodeRequest
import com.nyth.app.core.model.remote.response.RefreshTokenResponse
import com.nyth.app.core.model.remote.response.TokenResponse
import com.nyth.app.core.network.service.IdentityService
import okhttp3.MultipartBody
import retrofit2.Response
import javax.inject.Inject

class IdentityClient @Inject constructor(
    private val identityService: IdentityService
) {
    // region Account
    suspend fun getAccountById(accountId: String) =
        identityService.getAccountById(accountId = accountId)

    suspend fun getAccountProfileById(accountId: String,classifiedAdId : String) =
        identityService.getAccountProfileById(accountId = accountId, classifiedAdId = classifiedAdId)

    suspend fun getProfile() =
        identityService.getProfile()

    suspend fun getProfileById(accountId: String) =
        identityService.getProfileById(accountId = accountId)

    suspend fun putChangeName(
        accountId: String,
        request: PutChangeNameRequest
    ) = identityService.putChangeName(
        accountId = accountId,
        request = request
    )

    suspend fun putProfileImage(
        file: MultipartBody.Part
    ) = identityService.putProfileImage(
        file = file
    )

    suspend fun putProfileCoverImage(
        file: MultipartBody.Part
    ) = identityService.putProfileCoverImage(
        file = file
    )

    suspend fun deleteAccountById(accountId: String) =
        identityService.deleteAccountById(accountId = accountId)

    suspend fun deleteAccountCoverImage() =
        identityService.deleteAccountCoverImage()

    suspend fun requestPhoneCode(request: RequestPhoneCodeRequest) =
        identityService.requestPhoneCode(request = request)

    suspend fun confirmPhoneNumber(request: ConfirmPhoneNumberRequest) =
        identityService.confirmPhoneNumber(request = request)

    suspend fun requestEmailCode(request: RequestEmailCodeRequest) =
        identityService.requestEmailCode(request = request)

    suspend fun confirmEmail(request: ConfirmEmailRequest) =
        identityService.confirmEmail(request = request)

    // endregion

    // region Authentication
    suspend fun requestCode(request: RequestCodeRequest) =
        identityService.requestCode(request = request)

    suspend fun register(request: RegisterRequest) =
        identityService.register(request = request)

    suspend fun externalRegister(request: ExternalRegisterRequest) =
        identityService.externalRegister(request = request)

    suspend fun confirmAccount(request: ConfirmAccountRequest) =
        identityService.confirmAccount(request = request)

    // endregion

    // region Packages
    suspend fun getPackagesCheck(packageActionType: String?) =
        identityService.getPackagesCheck(packageActionType = packageActionType)
    // endregion

    // region Role

    // endregion

    // region Connect
    suspend fun token(
        username: String,
        password: String,
        clientId: String,
        clientSecret: String
    ): Response<TokenResponse> =
        identityService.token(
            username = username,
            password = password,
            clientId = clientId,
            clientSecret = clientSecret
        )

    suspend fun tokenExternalLogin(
        provider: String,
        scope: String,
        identityToken: String,
        grantType: String,
        clientId: String,
        clientSecret: String,
    ): Response<TokenResponse> =
        identityService.tokenExternalLogin(
            provider = provider,
            scope = scope,
            identityToken = identityToken,
            grantType = grantType,
            clientId = clientId,
            clientSecret = clientSecret
        )

    suspend fun refreshToken(
        password: String,
        clientId: String,
        clientSecret: String
    ): Response<RefreshTokenResponse> =
        identityService.refreshToken(
            password = password,
            clientId = clientId,
            clientSecret = clientSecret
        )
    // endregion
}
