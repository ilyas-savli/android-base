package com.nyth.app.core.network.service

import com.nyth.app.core.model.local.enums.GrantType
import com.nyth.app.core.model.remote.response.RefreshTokenResponse
import com.nyth.app.core.model.remote.response.TokenResponse
import com.nyth.app.core.model.remote.response.pray.PrayTimeResponse
import com.nyth.app.core.model.utils.AppConstants
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface PrayService {
    object Endpoint {
        const val daily = "daily/{city}.json"
        const val clientId = "client_id"
        const val clientSecret = "client_secret"
    }

    @GET(Endpoint.daily)
    suspend fun getPrayTimeDaily(
        @Path("city") city: String,
        @Query("key") key: String
    ): Response<PrayTimeResponse>

}
