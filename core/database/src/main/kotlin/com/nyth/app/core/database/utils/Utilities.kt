package com.nyth.app.core.database.utils

import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.first
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import timber.log.Timber

/**
 * Converting String To Object
 * @param value to object which send via reified
 */
inline fun <reified T> convertToObject(value: String?): T? = try {
    when (T::class) {
        Boolean::class -> value.toBoolean() as T
        Float::class -> value?.toFloatOrNull() as T
        Int::class -> value?.toIntOrNull() as T
        Long::class -> value?.toLongOrNull() as T
        Double::class -> value?.toDoubleOrNull() as T
        String::class -> value as T
        else -> {
            val json = Json { ignoreUnknownKeys = true }
            json.decodeFromString<T>(value.orEmpty())
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

fun SharedPreferences.Editor.putEncryptedString(key: String, value: String?) {
    val encrypted = EncryptionSerializer.encrypt(value)
    putString(key, encrypted).apply()
}

fun SharedPreferences.getEncryptedString(key: String, default: String? = null): String? {
    val encrypted = getString(key, null) ?: return default
    return try {
        EncryptionSerializer.decrypt(encrypted)
    } catch (e: Exception) {
        Timber.e(e)
        default
    }
}

suspend fun DataStore<Preferences>.putEncryptedString(key: Preferences.Key<String>, value: String) {
    val encrypted = EncryptionSerializer.encrypt(value)
    edit { it[key] = encrypted }
}

suspend fun DataStore<Preferences>.getEncryptedString(key: Preferences.Key<String>): String? {
    val prefs = data.first()
    val encrypted = prefs[key] ?: return null
    return try {
        EncryptionSerializer.decrypt(encrypted)
    } catch (e: Exception) {
        Timber.e(e)
        null
    }
}