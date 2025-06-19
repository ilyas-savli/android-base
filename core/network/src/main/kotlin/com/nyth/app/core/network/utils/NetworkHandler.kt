package com.nyth.app.core.network.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.nyth.app.core.database.utils.convertToObject
import com.nyth.app.core.model.remote.network.Result
import com.nyth.app.core.model.remote.response.ErrorResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import retrofit2.HttpException
import retrofit2.Response
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

    inline fun <T> safeApiFlow(crossinline apiCall: suspend () -> Response<T>): Flow<Result<T>> =
        channelFlow {
            send(Result.Loading)
            try {
                if (!hasInternet()) {
                    send(Result.Error(message = "No Internet Connection"))
                    return@channelFlow
                }
                val response = apiCall()
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        send(Result.Success(body))
                    } else {
                        send(
                            Result.Error(
                                message = "Empty response",
                                code = response.code()
                            )
                        )
                    }
                } else {
                    val errorResponse =
                        convertToObject<ErrorResponse>(response.errorBody()?.string())
                    send(Result.Error(error = errorResponse, code = response.code()))
                }
            } catch (e: SocketTimeoutException) {
                send(Result.Error(message = "SocketTimeoutException: ${e.localizedMessage}"))
            } catch (e: IllegalStateException) {
                send(Result.Error(message = "IllegalStateException: ${e.localizedMessage}"))
            } catch (e: UnknownHostException) {
                send(Result.Error(message = "UnknownHostException: ${e.localizedMessage}"))
            } catch (e: IOException) {
                send(Result.Error(message = "IOException: ${e.localizedMessage}"))
            } catch (e: HttpException) {
                send(
                    Result.Error(
                        message = "HTTP error: ${e.localizedMessage}",
                        code = e.code()
                    )
                )
            } catch (e: Exception) {
                send(Result.Error(message = "Unexpected error: ${e.localizedMessage}"))
            }
        }
}




