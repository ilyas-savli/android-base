package com.nyth.app.core.network.repository

import com.nyth.app.core.model.remote.network.Result
import com.nyth.app.core.model.remote.response.pray.PrayTimeResponse
import com.nyth.app.core.network.service.UserService
import com.nyth.app.core.network.utils.NetworkHandler
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userService: UserService,
    private val networkHandler: NetworkHandler
) {
    suspend fun getPrayTimes(
        key: String,
        city: String
    ): Flow<Result<PrayTimeResponse>> = networkHandler.safeApiFlow(
        apiCall = {
            userService.getUserInfos(
                key = key,
                city = city
            )
        }
    )
}