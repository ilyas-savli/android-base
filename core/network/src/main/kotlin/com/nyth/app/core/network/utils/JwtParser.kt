package com.nyth.app.core.network.utils

import com.auth0.android.jwt.JWT
import com.nyth.app.core.database.utils.convertToObject

/**
 * Converting JWT To Object
 */
object JwtParser {
    inline fun <reified T> JWT.toAuthObject(): T? {
        val arr = this.claims.map {
            String.format("\"%s\":\"%s\"", it.key, it.value.asString())
        }
        val json = buildString {
            append("{")
            append(arr.joinToString(separator = ","))
            append("}")
        }

        val result = convertToObject<T>(json)
        return result
    }
}