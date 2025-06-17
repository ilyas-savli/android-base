package com.nyth.app.main.domain

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.nyth.app.core.designsystem.platform.navigation.Screen
import com.nyth.app.core.network.utils.AuthManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val authManager: AuthManager,
) : ViewModel() {
    val backStack = mutableStateListOf<Screen>(Screen.Splash)
}
