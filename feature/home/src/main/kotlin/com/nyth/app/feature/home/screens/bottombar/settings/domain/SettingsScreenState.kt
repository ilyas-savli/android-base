package com.nyth.app.feature.home.screens.bottombar.settings.domain

import com.nyth.app.core.designsystem.platform.viewmodel.BaseViewState
import com.nyth.app.core.model.local.enums.UiState

data class SettingsScreenState(
    val errorMessage: String? = null,
    val selectedCity: String? = null,
    override val uiState: UiState = UiState.LOADING
) : BaseViewState