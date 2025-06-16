package com.nyth.app.core.network.di

import android.content.Context
import coil3.ImageLoader
import coil3.disk.DiskCache
import coil3.disk.directory
import coil3.memory.MemoryCache
import coil3.network.okhttp.OkHttpNetworkFetcherFactory
import coil3.request.crossfade
import coil3.svg.SvgDecoder
import com.nyth.app.core.network.image.ImageCacheInterceptor
import com.nyth.app.core.network.image.ImageRetryInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ImageLoaderModule {
    @Provides
    @Singleton
    fun provideImageLoader(@ApplicationContext context: Context): ImageLoader {
        val okHttpClient = OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS).retryOnConnectionFailure(true)
            .addInterceptor(ImageRetryInterceptor(maxRetries = 2)) // Optional manual retry
            .addInterceptor(ImageCacheInterceptor()).build()

        return ImageLoader.Builder(context).memoryCache {
            MemoryCache.Builder()
                .maxSizePercent(context = context, percent = 0.25) // 25% of available memory
                .build()
        }.diskCache {
            DiskCache.Builder().directory(context.cacheDir.resolve("image_cache"))
                .maxSizeBytes(100L * 1024 * 1024) // 100 MB disk cache
                .build()
        }.components {
            add(OkHttpNetworkFetcherFactory(okHttpClient))
            add(SvgDecoder.Factory()) // Supports SVGs if needed
        }.crossfade(true) // Smooth fade transition
            .build()
    }
}