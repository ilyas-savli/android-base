package com.nyth.app.core.database.utils

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import timber.log.Timber

/**
 * Converting String To Object
 * @param value to object which send via reified
 */
inline fun <reified T> convertToObject(value: String): T? = try {
    when (T::class) {
        Boolean::class -> value.toBoolean() as T
        Float::class -> value.toFloat() as T
        Int::class -> value.toInt() as T
        Long::class -> value.toLong() as T
        Double::class -> value.toDouble() as T
        String::class -> value as T
        else -> {
            val json = Json { ignoreUnknownKeys = true }
            json.decodeFromString<T>(value)
        }
    }
} catch (e: Exception) {
    Timber.e(e.message ?: "Deserialization error")
    null
}

/**
 * Converting Object To String
 * @param value type [T] to [String]
 */
inline fun <reified T> convertToString(value: T): String? = try {
    when (T::class) {
        Boolean::class, Float::class, Int::class, Long::class, Double::class, String::class -> value.toString()
        else -> {
            val json = Json { ignoreUnknownKeys = true }
            json.encodeToString(value)
        }
    }
} catch (e: Exception) {
    Timber.e(e)
    null
}