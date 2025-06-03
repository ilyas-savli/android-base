package com.nyth.app.core.network.repository

import com.nyth.app.core.network.service.PrayService
import com.nyth.app.core.network.utils.NetworkHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PrayRepository @Inject constructor(
    private val prayService: PrayService,
    private val ioDispatcher: CoroutineDispatcher,
    private val networkHandler: NetworkHandler,
) {
    // region Course
    suspend fun getPrayTimes(
        key: String,
        city: String
    ) = networkHandler.handleResponse(
        request = {
            prayService.getPrayTimeDaily(
                key = key,
                city = city
            )
        }
    ).flowOn(ioDispatcher)

}