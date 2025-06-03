package com.nyth.app.core.database

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.nyth.app.core.database.sharedpref.SharedPreferenceManager
import com.nyth.app.core.database.utils.convertToObject
import com.nyth.app.core.database.utils.convertToString
import com.nyth.app.core.model.local.UserModel
import com.nyth.app.core.model.remote.request.ListingFilterRequest
import com.nyth.app.core.model.remote.response.ErrorResponse
import com.nyth.app.core.model.remote.response.ListingPreviewResponse
import com.nyth.app.core.model.remote.response.TokenResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import java.io.IOException
import javax.inject.Inject

class EncryptedDataStoreManager @Inject constructor(
    val dataStore: DataStore<Preferences>,
    private val sharedPref: SharedPreferenceManager
) {
    inline fun <reified T> getValue(
        key: String, defaultValue: T
    ): Flow<T> = dataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { preferences ->
        val value = preferences[stringPreferencesKey(key)]
        if (value.isNullOrEmpty()) {
            return@map defaultValue
        }

        convertToObject(value = value) ?: defaultValue
    }

    suspend inline fun <reified T> setValue(key: String, newValue: T) {
        convertToString(value = newValue)?.let { newValueNotNull ->
            dataStore.edit { mutablePreferences ->
                mutablePreferences[stringPreferencesKey(key)] = newValueNotNull
            }
        }
    }

    var generalPopUpMessage: Flow<ErrorResponse?>
        get() = getValue(
            key = GENERAL_POPUP_MESSAGE_KEY, defaultValue = null
        )
        set(value) {
            runBlocking(Dispatchers.IO) {
                value.collect {
                    setValue(GENERAL_POPUP_MESSAGE_KEY, it)
                }
            }
        }

    val token: Flow<String?>
        get() = getValue(
            key = TOKEN_KEY, defaultValue = null
        )
    var tokenResponse: Flow<TokenResponse?>
        get() = getValue(
            key = TOKEN_RESPONSE_KEY, defaultValue = null
        )
        set(value) {
            runBlocking(Dispatchers.IO) {
                value.collectLatest {
                    sharedPref.accessToken = it?.accessToken
                    setValue(TOKEN_RESPONSE_KEY, it)
                }
            }
        }

    var listingPreview: Flow<ListingPreviewResponse?>
        get() = getValue(
            key = LISTING_PREVIEW_KEY, defaultValue = null
        )
        set(value) {
            runBlocking(Dispatchers.IO) {
                value.collectLatest {
                    setValue(LISTING_PREVIEW_KEY, it)
                }
            }
        }

    var listingFilterRequest: Flow<ListingFilterRequest?>
        get() = getValue(
            key = LISTING_FILTER_REQUEST_KEY, defaultValue = null
        )
        set(value) {
            runBlocking(Dispatchers.IO) {
                value.collectLatest {
                    setValue(LISTING_FILTER_REQUEST_KEY, it)
                }
            }
        }

    var searchQueryHistory: Flow<List<String>>
        get() = getValue(
            key = SEARCH_QUERY_HISTORY_KEY, defaultValue = emptyList()
        )
        set(value) {
            runBlocking(Dispatchers.IO) {
                value.collect {
                    setValue(SEARCH_QUERY_HISTORY_KEY, it)
                }
            }
        }

    var userModel: Flow<UserModel?>
        get() = getValue(
            key = USER_MODEL_KEY, defaultValue = null
        )
        set(value) {
            runBlocking(Dispatchers.IO) {
                value.collect {
                    setValue(USER_MODEL_KEY, it)
                }
            }
        }

    suspend fun clear() {
        dataStore.edit { mutablePreferences -> mutablePreferences.clear() }
    }

    companion object {
        private const val GENERAL_POPUP_MESSAGE_KEY = "GENERAL_POPUP_MESSAGE_KEY"
        private const val TOKEN_KEY = "TOKEN_KEY"
        private const val TOKEN_RESPONSE_KEY = "TOKEN_RESPONSE_KEY"
        private const val LISTING_PREVIEW_KEY = "LISTING_PREVIEW_KEY"
        private const val LISTING_FILTER_REQUEST_KEY = "LISTING_FILTER_REQUEST_KEY"
        private const val SEARCH_QUERY_HISTORY_KEY = "SEARCH_QUERY_HISTORY_KEY"
        private const val USER_MODEL_KEY = "USER_MODEL_KEY"
    }
}
