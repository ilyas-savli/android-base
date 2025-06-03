package com.nyth.app.core.network.utils

import com.auth0.android.jwt.JWT
import com.nyth.app.core.model.utils.JsonSerializer.toObject

/**
 * Converting JWT To Object
 */
object JwtParser {
    inline fun <reified T> JWT.toAuthObject(): T? {
        val arr = this.claims.map {
            kotlin.String.format("\"%s\":\"%s\"", it.key, it.value.asString())
        }
        val json = buildString {
            append("{")
            append(arr.joinToString(separator = ","))
            append("}")
        }

        val result = json.toObject<T>()
        return result
    }
}