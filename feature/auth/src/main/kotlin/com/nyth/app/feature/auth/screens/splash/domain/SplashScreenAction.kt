package com.nyth.app.feature.auth.screens.splash.domain

import com.nyth.app.core.designsystem.platform.viewmodel.BaseAction

sealed class SplashScreenAction : BaseAction {
    data class OnFailure(val errorMessage: String?) : SplashScreenAction()
    object OnLoading : SplashScreenAction()
    object CheckUserLoggedIn : SplashScreenAction()
    object NavigateToLogin : SplashScreenAction()
    object NavigateToListing : SplashScreenAction()
}
