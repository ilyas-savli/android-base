package com.nyth.app.core.database.sharedpref

import android.content.Context
import android.content.SharedPreferences
import com.nyth.app.core.database.utils.convertToObject
import com.nyth.app.core.database.utils.convertToString
import com.nyth.app.core.database.utils.getEncryptedString
import com.nyth.app.core.database.utils.putEncryptedString
import javax.inject.Inject

class SharedPreferenceManager @Inject constructor(context: Context) {
    var prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "AppSharedPref"
        private const val ACCESS_TOKEN = "access_token"
        private const val SELECTED_CITY = "selected_city"
    }

    var accessToken: String?
        get() = get(ACCESS_TOKEN, null)
        set(value) {
            set(ACCESS_TOKEN, value)
        }

    var selectedCity: String?
        get() = get(SELECTED_CITY, null)
        set(value) {
            set(SELECTED_CITY, value)
        }

    fun clear() {
        prefs.edit { it.clear() }
    }

    inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        operation(editor)
        editor.apply()
    }

    inline operator fun <reified T:Any> set(key: String, value: T?) {
        prefs.edit { it.putEncryptedString(key = key, value = convertToString(value = value)) }
    }

    inline operator fun <reified T : Any> get(
        key: String, defaultValue: T? = null
    ): T? = prefs.getEncryptedString(key = key)?.let { convertToObject(value = it) } ?: defaultValue
}