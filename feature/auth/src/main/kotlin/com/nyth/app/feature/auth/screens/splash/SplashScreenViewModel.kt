package com.nyth.app.feature.auth.screens.splash

import androidx.lifecycle.ViewModel
import com.nyth.app.core.network.utils.AuthManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val authManager: AuthManager
) : ViewModel() {
    fun checkUserLoggedIn(): Boolean {
        return authManager.currentUser != null
    }
}
