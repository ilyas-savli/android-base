package com.nyth.app.core.model.remote.network

import com.nyth.app.core.model.remote.response.ErrorResponse

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val error: ErrorResponse?,
    val errorList: List<String?>? = null
) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(status = Status.SUCCESS, data = data, error = null)
        }

        fun <T> error(error: ErrorResponse?): Resource<T> {
            return Resource(status = Status.ERROR, data = null, error = error, errorList = error?.errorList)
        }
    }
}
