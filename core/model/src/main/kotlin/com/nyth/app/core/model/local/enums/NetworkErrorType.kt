package com.nyth.app.core.model.local.enums

enum class NetworkErrorType(val code: Int) {
    NoInternet(code = -1),
    Error(code = 400),
    UnAuthorized(code = 401),
    Forbidden(code = 403),
    UnExpected(code = 0),
    TimeOut(code = -2);

    companion object {
        fun findFromCode(code: Int) = values().firstOrNull { it.code == code } ?: UnExpected
    }
}