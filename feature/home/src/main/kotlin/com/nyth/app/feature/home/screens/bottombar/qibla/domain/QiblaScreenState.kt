package com.nyth.app.feature.home.screens.bottombar.qibla.domain

import com.nyth.app.core.designsystem.platform.viewmodel.BaseViewState
import com.nyth.app.core.model.local.enums.UiState

data class QiblaScreenState(
    val errorMessage: String? = null,
    override val uiState: UiState = UiState.LOADING
) : BaseViewState