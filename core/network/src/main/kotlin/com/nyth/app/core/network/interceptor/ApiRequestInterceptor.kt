package com.nyth.app.core.network.interceptor

import android.os.Build
import com.auth0.android.jwt.JWT
import com.nyth.app.core.database.EncryptedDataStoreManager
import com.nyth.app.core.model.annotation.Authenticated
import com.nyth.app.core.model.ext.MappingExt.toTokenResponse
import com.nyth.app.core.model.ext.StringExt.empty
import com.nyth.app.core.model.local.UserModel
import com.nyth.app.core.network.service.RefreshTokenService
import com.nyth.app.core.network.utils.AuthManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import retrofit2.Invocation
import timber.log.Timber
import javax.inject.Inject

class ApiRequestInterceptor @Inject constructor(
    private val dataStore: EncryptedDataStoreManager,
    private val refreshTokenService: RefreshTokenService,
    private val authManager: AuthManager,
) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = addHeaderToRequest(chain.request())

        return chain.proceed(newRequest)
    }

    private fun addHeaderToRequest(request: Request): Request {
        val newRequest = request.newBuilder()

        runBlocking {
            try {
                val annotations =
                    request.tag(Invocation::class.java)?.method()?.annotations

                val shouldAuthenticate =
                    annotations?.any { it1 -> it1.annotationClass == Authenticated::class }
                        ?: false

                if (shouldAuthenticate) {
                    dataStore.tokenResponse.first()?.accessToken?.let { accessToken ->
                        val newAccessToken = if (JWT(accessToken).isExpired(10)) {
                            Timber.d("TokenLog ${request.url} $accessToken")
                            val response = refreshTokenService.refreshToken(
                                refreshToken = dataStore.tokenResponse.first()?.refreshToken
                                    ?: String.empty,
                                clientId = "client",
                                clientSecret = "secret"
                            )

                            val newTokenResponse = if (response.isSuccessful) {
                                dataStore.tokenResponse = flowOf(response.body()?.toTokenResponse())
                                authManager.currentUser.let {
                                    val account = UserModel(
                                        userId = it?.userId,
                                        firstName = it?.firstName,
                                        lastName = it?.lastName,
                                        email = it?.email,
                                        type = it?.type,
                                    )
                                    dataStore.userModel = flowOf(account)
                                }
                                response.body()?.toTokenResponse()
                            } else {
                                dataStore.tokenResponse = flowOf(null)
                                null
                            }

                            newTokenResponse?.accessToken
                        } else {
                            accessToken
                        }
                        newRequest.addHeader(
                            "Authorization",
                            "Bearer $newAccessToken"
                        )
                    }
                }
                newRequest.addHeader(
                    "User-Agent",
                    "androidbase/1.0.0 (com.nyth.app; build:1; " +
                            "android:${Build.VERSION.SDK_INT}; ${Build.MODEL}; ${Build.MANUFACTURER})"
                )
                newRequest.addHeader(
                    "x-client-type",
                    "android"
                )
                newRequest.build()
            } catch (e: Exception) {
                Timber.e(e)
            }
        }

        return newRequest.build()
    }
}
