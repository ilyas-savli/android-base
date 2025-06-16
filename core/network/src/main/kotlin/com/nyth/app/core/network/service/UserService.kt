package com.nyth.app.core.network.service

import com.nyth.app.core.model.remote.response.pray.PrayTimeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {
    object Endpoint {
        const val daily = "daily/{city}.json"
    }

    @GET(Endpoint.daily)
    suspend fun getUserInfos(
        @Path("city") city: String,
        @Query("key") key: String
    ): Response<PrayTimeResponse>
}
