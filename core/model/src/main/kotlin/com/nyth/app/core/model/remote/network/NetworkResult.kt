package com.nyth.app.core.model.remote.network

import com.nyth.app.core.model.remote.response.ErrorResponse

sealed class NetworkResult<out T> {
    data object Loading : NetworkResult<Nothing>()
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class Error(
        val message: String? = null,
        val error: ErrorResponse? = null,
        val code: Int? = null
    ) : NetworkResult<Nothing>()
}
