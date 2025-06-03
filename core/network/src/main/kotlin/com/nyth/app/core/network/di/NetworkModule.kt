package com.nyth.app.core.network.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.nyth.app.core.database.EncryptedDataStoreManager
import com.nyth.app.core.database.sharedpref.SharedPreferenceManager
import com.nyth.app.core.network.BuildConfig
import com.nyth.app.core.network.interceptor.ApiRequestInterceptor
import com.nyth.app.core.network.service.ContentService
import com.nyth.app.core.network.service.IdentityService
import com.nyth.app.core.network.service.ListingService
import com.nyth.app.core.network.service.PrayService
import com.nyth.app.core.network.service.RefreshTokenService
import com.nyth.app.core.network.utils.AuthManager
import com.nyth.app.core.network.utils.DEFAULT_CALL_TIMEOUT_MILLIS
import com.nyth.app.core.network.utils.DEFAULT_CONNECT_TIMEOUT_MILLIS
import com.nyth.app.core.network.utils.DEFAULT_READ_TIMEOUT_MILLIS
import com.nyth.app.core.network.utils.DEFAULT_WRITE_TIMEOUT_MILLIS
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    /**
     * logging interceptor
     */
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        if (BuildConfig.DEBUG) {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            setLevel(HttpLoggingInterceptor.Level.NONE)
        }
    }

    /**
     * okhttp
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(
        chuckerInterceptor: ChuckerInterceptor,
        apiRequestInterceptor: ApiRequestInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(apiRequestInterceptor)
            .addInterceptor(chuckerInterceptor)
            .addInterceptor(provideLoggingInterceptor())
            .callTimeout(DEFAULT_CALL_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
            .connectTimeout(DEFAULT_CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
            .readTimeout(DEFAULT_READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
            .writeTimeout(DEFAULT_WRITE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
            .build()

    /**
     * moshi
     */
    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
        Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    /**
     * chucker
     */
    @Singleton
    @Provides
    fun provideChuckerInterceptor(@ApplicationContext context: Context): ChuckerInterceptor =
        ChuckerInterceptor.Builder(context)
            .build()

    /**
     * api request interceptor
     */
    @Singleton
    @Provides
    fun provideApiRequestInterceptor(
        dataStore: EncryptedDataStoreManager,
        refreshTokenService: RefreshTokenService,
        authManager: AuthManager
    ): ApiRequestInterceptor =
        ApiRequestInterceptor(
            dataStore = dataStore,
            refreshTokenService = refreshTokenService,
            authManager = authManager
        )

    /**
     * identity service
     */
    @Provides
    @Singleton
    fun provideIdentityService(okHttpClient: OkHttpClient, moshi: Moshi): IdentityService =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.IDENTITY_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi)).build()
            .create(IdentityService::class.java)

    /**
     * content service
     */
    @Provides
    @Singleton
    fun provideContentService(okHttpClient: OkHttpClient, moshi: Moshi): ContentService =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.CONTENT_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi)).build()
            .create(ContentService::class.java)


    /**
     * listing service
     */
    @Provides
    @Singleton
    fun provideListingService(okHttpClient: OkHttpClient, moshi: Moshi): ListingService =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.LISTING_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi)).build()
            .create(ListingService::class.java)

    /**
     * refreshToken service
     */
    @Provides
    @Singleton
    fun provideRefreshTokenService(
        moshi: Moshi,
        chuckerInterceptor: ChuckerInterceptor
    ): RefreshTokenService =
        Retrofit.Builder()
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(chuckerInterceptor)
                    .addInterceptor(provideLoggingInterceptor())
                    .callTimeout(DEFAULT_CALL_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                    .connectTimeout(DEFAULT_CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                    .readTimeout(DEFAULT_READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                    .writeTimeout(DEFAULT_WRITE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                    .build()
            )
            .baseUrl(BuildConfig.IDENTITY_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi)).build()
            .create(RefreshTokenService::class.java)


    @Provides
    @Singleton
    fun providePrayService(okHttpClient: OkHttpClient, moshi: Moshi): PrayService =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.PRAY_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi)).build()
            .create(PrayService::class.java)

    /**
     * AuthManager
     */
    @Provides
    @Singleton
    fun provideAuthManager(
        sharedPreferenceManager: SharedPreferenceManager
    ): AuthManager = AuthManager(sharedPreferenceManager)
}
