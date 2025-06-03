package com.nyth.app.core.network.repository

import com.nyth.app.core.model.remote.request.ConfirmAccountRequest
import com.nyth.app.core.model.remote.request.ConfirmEmailRequest
import com.nyth.app.core.model.remote.request.ConfirmPhoneNumberRequest
import com.nyth.app.core.model.remote.request.ExternalRegisterRequest
import com.nyth.app.core.model.remote.request.PutChangeNameRequest
import com.nyth.app.core.model.remote.request.RegisterRequest
import com.nyth.app.core.model.remote.request.RequestCodeRequest
import com.nyth.app.core.model.remote.request.RequestEmailCodeRequest
import com.nyth.app.core.model.remote.request.RequestPhoneCodeRequest
import com.nyth.app.core.network.client.IdentityClient
import com.nyth.app.core.network.utils.NetworkHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody
import javax.inject.Inject

class IdentityRepository @Inject constructor(
    private val identityClient: IdentityClient,
    private val ioDispatcher: CoroutineDispatcher,
    private val networkHandler: NetworkHandler
) {
    // region Account
    suspend fun getAccountById(accountId: String) =
        networkHandler.handleResponse(request = {
            identityClient.getAccountById(accountId = accountId)
        }).flowOn(ioDispatcher)

    suspend fun getAccountProfileById(accountId: String, classifiedAdId: String) =
        networkHandler.handleResponse(request = {
            identityClient.getAccountProfileById(
                accountId = accountId,
                classifiedAdId = classifiedAdId
            )
        }).flowOn(ioDispatcher)

    suspend fun getProfile() =
        networkHandler.handleResponse(request = {
            identityClient.getProfile()
        }).flowOn(ioDispatcher)

    suspend fun getProfileById(accountId: String) =
        networkHandler.handleResponse(request = {
            identityClient.getProfileById(accountId = accountId)
        }).flowOn(ioDispatcher)

    suspend fun putChangeName(
        accountId: String,
        request: PutChangeNameRequest
    ) = networkHandler.handleResponse(request = {
        identityClient.putChangeName(accountId = accountId, request = request)
    }).flowOn(ioDispatcher)


    suspend fun putProfileImage(
        file: MultipartBody.Part
    ) = networkHandler.handleResponse(request = {
        identityClient.putProfileImage(file = file)
    }).flowOn(ioDispatcher)

    suspend fun putProfileCoverImage(
        file: MultipartBody.Part
    ) = networkHandler.handleResponse(request = {
        identityClient.putProfileCoverImage(file = file)
    }).flowOn(ioDispatcher)

    suspend fun deleteAccountById(accountId: String) =
        networkHandler.handleResponse(request = {
            identityClient.deleteAccountById(accountId = accountId)
        }).flowOn(ioDispatcher)

    suspend fun deleteAccountCoverImage() =
        networkHandler.handleResponse(request = {
            identityClient.deleteAccountCoverImage()
        }).flowOn(ioDispatcher)

    suspend fun requestPhoneCode(request: RequestPhoneCodeRequest) =
        networkHandler.handleResponse(request = {
            identityClient.requestPhoneCode(request = request)
        }).flowOn(ioDispatcher)

    suspend fun confirmPhoneNumber(request: ConfirmPhoneNumberRequest) =
        networkHandler.handleResponse(request = {
            identityClient.confirmPhoneNumber(request = request)
        }).flowOn(ioDispatcher)

    suspend fun requestEmailCode(request: RequestEmailCodeRequest) =
        networkHandler.handleResponse(request = {
            identityClient.requestEmailCode(request = request)
        }).flowOn(ioDispatcher)

    suspend fun confirmEmail(request: ConfirmEmailRequest) =
        networkHandler.handleResponse(request = {
            identityClient.confirmEmail(request = request)
        }).flowOn(ioDispatcher)

    // endregion

    // region Authentication
    suspend fun requestCode(request: RequestCodeRequest) =
        networkHandler.handleResponse(request = {
            identityClient.requestCode(request = request)
        }).flowOn(ioDispatcher)

    suspend fun register(request: RegisterRequest) =
        networkHandler.handleResponse(request = {
            identityClient.register(request = request)
        }).flowOn(ioDispatcher)

    suspend fun externalRegister(request: ExternalRegisterRequest) =
        networkHandler.handleResponse(request = {
            identityClient.externalRegister(request = request)
        }).flowOn(ioDispatcher)

    suspend fun confirmAccount(request: ConfirmAccountRequest) =
        networkHandler.handleResponse(request = {
            identityClient.confirmAccount(request = request)
        }).flowOn(ioDispatcher)

    // endregion

    // region Packages
    suspend fun getPackagesCheck(packageActionType: String? = null) =
        networkHandler.handleResponse(request = {
            identityClient.getPackagesCheck(packageActionType = packageActionType)
        }).flowOn(ioDispatcher)
    // endregion

    // region Role

    // endregion

    // region Connect
    suspend fun token(
        username: String,
        password: String,
        clientId: String,
        clientSecret: String
    ) = networkHandler.handleResponse(request = {
        identityClient.token(
            username = username,
            password = password,
            clientId = clientId,
            clientSecret = clientSecret
        )
    }).flowOn(ioDispatcher)

    suspend fun tokenExternalLogin(
        provider: String,
        scope: String,
        identityToken: String,
        grantType: String,
        clientId: String,
        clientSecret: String
    ) = networkHandler.handleResponse(request = {
        identityClient.tokenExternalLogin(
            provider = provider,
            scope = scope,
            identityToken = identityToken,
            grantType = grantType,
            clientId = clientId,
            clientSecret = clientSecret
        )
    }).flowOn(ioDispatcher)

    suspend fun refreshToken(
        password: String,
        clientId: String,
        clientSecret: String
    ) = networkHandler.handleResponse(request = {
        identityClient.refreshToken(
            password = password,
            clientId = clientId,
            clientSecret = clientSecret
        )
    }).flowOn(ioDispatcher)

    // endregion
}
