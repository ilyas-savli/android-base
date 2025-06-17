package com.nyth.app.core.network.utils

import com.auth0.android.jwt.JWT
import com.nyth.app.core.database.sharedpref.SharedPreferenceManager
import com.nyth.app.core.model.local.AuthModel
import com.nyth.app.core.network.utils.JwtParser.toAuthObject
import javax.inject.Inject

class AuthManager @Inject constructor(
    private val sharedPref: SharedPreferenceManager
) {
    private val token: String?
        get() = sharedPref.accessToken

    val currentUser: AuthModel?
        get() = token?.let {
            try {
                JWT(it).toAuthObject<AuthModel>()?.copy(token = it)
            } catch (e: Exception) {
                null
            }
        }

    fun login(token: String) {
        sharedPref.accessToken = token
    }

    fun logout() {
        sharedPref.accessToken = null
    }
}