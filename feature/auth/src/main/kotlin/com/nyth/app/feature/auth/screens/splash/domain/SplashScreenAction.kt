package com.nyth.app.feature.auth.screens.splash.domain

import com.nyth.app.core.designsystem.platform.viewmodel.BaseAction

sealed class SplashScreenAction : BaseAction {
    data class OnFailure(val errorMessage: String?) : SplashScreenAction()
    data object OnLoading : SplashScreenAction()
    data object CheckUserLoggedIn : SplashScreenAction()
    data object NavigateToLogin : SplashScreenAction()
    data object NavigateToListing : SplashScreenAction()
}
