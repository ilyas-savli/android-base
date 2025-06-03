package com.nyth.app.main.domain

import com.nyth.app.core.designsystem.platform.viewmodel.BaseViewState
import com.nyth.app.core.model.local.enums.UiState

data class MainScreenState(
    val errorMessage: String? = null,
    override val uiState: UiState = UiState.LOADING,
    val isUserLoggedIn: Boolean = false
) : BaseViewState
