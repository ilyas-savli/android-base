package com.nyth.app.feature.home.screens.bottombar.settings

import androidx.lifecycle.ViewModel
import com.nyth.app.core.network.utils.AuthManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsScreenViewModel @Inject constructor(
    private val authManager: AuthManager
) : ViewModel() {
    fun logoutUser() {
        authManager.logout()
    }

    fun getCurrentUser() = authManager.currentUser
}