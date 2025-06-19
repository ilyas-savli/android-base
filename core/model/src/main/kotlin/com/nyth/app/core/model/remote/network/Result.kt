package com.nyth.app.core.model.remote.network

import com.nyth.app.core.model.remote.response.ErrorResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed class Result<out T> {
    data object Loading : Result<Nothing>()
    data class Success<T>(val data: T) : Result<T>()
    data class Error(
        val message: String? = null,
        val error: ErrorResponse? = null,
        val code: Int? = null,
        val throwable: Throwable? = null
    ) : Result<Nothing>()
}

fun <T> Flow<T>.asResult(): Flow<Result<T>> =
    map<T, Result<T>> { Result.Success(it) }.onStart { emit(Result.Loading) }
        .catch { emit(Result.Error(throwable = it)) }