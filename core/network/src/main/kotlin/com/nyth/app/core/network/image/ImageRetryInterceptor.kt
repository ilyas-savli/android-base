package com.nyth.app.core.network.image

import okhttp3.Interceptor
import okio.IOException

class ImageRetryInterceptor(
    private val maxRetries: Int = 1
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val request = chain.request()
        var response: okhttp3.Response? = null
        var tryCount = 0
        var lastException: IOException? = null

        do {
            try {
                response = chain.proceed(request)
                if (response.isSuccessful || tryCount >= maxRetries) {
                    return response
                }
            } catch (e: IOException) {
                lastException = e
                if (tryCount >= maxRetries) {
                    throw e
                }
            }
            tryCount++
        } while (tryCount <= maxRetries)

        // This should never be reached, but added for safety
        throw lastException ?: IOException("Unknown error in ImageRetryInterceptor")
    }
}