package com.nyth.app.main.domain

import com.nyth.app.core.designsystem.platform.viewmodel.BaseAction

sealed class MainScreenAction : BaseAction {
    data class OnFailure(val errorMessage: String?) : MainScreenAction()
    data object OnLoading : MainScreenAction()
    data class OnSuccess(val isUserLoggedIn: Boolean) : MainScreenAction()
}
