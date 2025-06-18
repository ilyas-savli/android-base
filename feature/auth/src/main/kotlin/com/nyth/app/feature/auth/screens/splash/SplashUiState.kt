package com.nyth.app.feature.auth.screens.splash

sealed interface SplashUiState {
    data object Loading : SplashUiState

    data object LoadFailed : SplashUiState

    data object NeedForceUpdate : SplashUiState

    data class Success(
        val isLoggedIn: Boolean
    ) : SplashUiState
}
