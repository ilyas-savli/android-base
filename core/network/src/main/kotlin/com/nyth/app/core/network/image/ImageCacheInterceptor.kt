package com.nyth.app.core.network.image

import okhttp3.Interceptor

class ImageCacheInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val response = chain.proceed(chain.request())
        if (response.code == 404 || response.code == 500 || !response.isSuccessful) {
            return response.newBuilder()
                .header("Cache-Control", "no-store")
                .build()
        }
        return response
    }
}