package com.nyth.app.feature.auth.screens.splash.domain

import com.nyth.app.core.designsystem.platform.viewmodel.BaseViewState
import com.nyth.app.core.model.local.enums.UiState

data class SplashScreenState(
    val errorMessage: String? = null,
    override val uiState: UiState = UiState.SUCCESS,
    val isNavigateToLogin: Boolean = false,
    val isNavigateToHome: Boolean = false,
) : BaseViewState
