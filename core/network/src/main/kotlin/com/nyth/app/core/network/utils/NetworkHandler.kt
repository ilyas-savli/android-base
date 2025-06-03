package com.nyth.app.core.network.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.nyth.app.core.database.utils.convertToObject
import com.nyth.app.core.model.ext.StringExt.empty
import com.nyth.app.core.model.local.enums.NetworkErrorType
import com.nyth.app.core.model.remote.network.Resource
import com.nyth.app.core.model.remote.response.ErrorResponse
import com.nyth.app.core.model.remote.response.ErrorStringResponse
import com.nyth.app.core.network.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class NetworkHandler @Inject constructor(
    val context: Context
) {
    fun hasInternet(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                return true
            }
        }
        return false
    }

    inline fun <T> handleResponse(crossinline request: suspend () -> Response<T>): Flow<Resource<T>> =
        channelFlow {
            var response: Response<T>? = null
            try {
                if (!hasInternet()) {
                    throw Exception()
                }
                response = request.invoke()

                if (response.isSuccessful) {
                    send(Resource.success(data = response.body()))
                } else {
                    throw HttpException(response)
                }
            } catch (e: UnknownHostException) {
                Timber.e(e)
                send(
                    response.defaultServerError().first()
                )
            } catch (e: HttpException) {
                Timber.e(e)
                send(
                    response.defaultServerError().first()
                )
            } catch (e: IllegalStateException) {
                Timber.e(e)
                send(
                    response.defaultServerError().first()
                )
            } catch (e: SocketTimeoutException) {
                Timber.e(e)
                send(
                    Resource.error(
                        error = ErrorResponse(
                            errorType = NetworkErrorType.TimeOut,
                            errorList = listOf(context.getString(R.string.error_timeout))
                        )
                    )
                )
            } catch (e: Exception) {
                Timber.e(e)
                send(
                    response.defaultServerError().first()
                )
            }
        }.flowOn(Dispatchers.IO)

    fun <T> Response<T>?.defaultServerError(): Flow<Resource<T>> =
        try {
            val errorCode = this?.code() ?: 0
            val errorBodyString = this?.errorBody()?.string() ?: String.empty

            Timber.e("Response Error -> " + errorBodyString + "Code: $errorCode")

            val errorResponse: ErrorResponse = if (!hasInternet()) {
                ErrorResponse(success = false, errorType = NetworkErrorType.NoInternet)
            } else {
                convertToObject<ErrorResponse>(errorBodyString)
                    ?: convertToObject<ErrorStringResponse>(errorBodyString)?.let {
                        ErrorResponse(
                            success = false,
                            errorType = NetworkErrorType.UnExpected,
                            errorList = it.errors
                        )
                    } ?: ErrorResponse(
                        success = false,
                        errorType = NetworkErrorType.findFromCode(code = errorCode)
                    )
            }

            flowOf(
                Resource.error(
                    error = errorResponse
                )
            )
        } catch (e: IOException) {
            Timber.e(e)
            flowOf(Resource.error(error = ErrorResponse(errorType = NetworkErrorType.UnExpected)))
        } catch (e: Exception) {
            Timber.e(e)
            flowOf(Resource.error(error = ErrorResponse(errorType = NetworkErrorType.UnExpected)))
        }
}