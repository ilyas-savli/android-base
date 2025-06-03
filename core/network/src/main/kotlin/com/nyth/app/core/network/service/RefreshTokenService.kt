package com.nyth.app.core.network.service

import com.nyth.app.core.model.local.enums.GrantType
import com.nyth.app.core.model.remote.response.RefreshTokenResponse
import com.nyth.app.core.model.remote.response.TokenResponse
import com.nyth.app.core.model.utils.AppConstants
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RefreshTokenService {
    object Endpoint {
        /**
         * Fields
         */
        const val username = "username"
        const val password = "password"
        const val grantType = "grant_type"
        const val clientId = "client_id"
        const val refreshToken = "refresh_token"
        const val clientSecret = "client_secret"
        const val scope = "scope"

        object Connect {
            private const val mainPath = "connect"
            const val token = "$mainPath/token"
        }
    }

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
    suspend fun refreshToken(
        @Field(Endpoint.refreshToken) refreshToken: String,
        @Field(Endpoint.grantType) grantType: String = GrantType.RefreshToken.type,
        @Field(Endpoint.clientId) clientId: String,
        @Field(Endpoint.clientSecret) clientSecret: String,
        @Field(Endpoint.scope) scope: String = AppConstants.SCOPE
    ): Response<RefreshTokenResponse>

    // endregion
}
