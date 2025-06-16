package com.nyth.app.core.network.repository

import com.nyth.app.core.network.service.UserService
import com.nyth.app.core.network.utils.NetworkHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userService: UserService,
    private val ioDispatcher: CoroutineDispatcher,
    private val networkHandler: NetworkHandler,
) {
    // region Course
    suspend fun getPrayTimes(
        key: String,
        city: String
    ) = networkHandler.handleResponse(
        request = {
            userService.getUserInfos(
                key = key,
                city = city
            )
        }
    ).flowOn(ioDispatcher)
}