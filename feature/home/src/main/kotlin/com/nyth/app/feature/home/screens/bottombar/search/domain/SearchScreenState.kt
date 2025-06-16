package com.nyth.app.feature.home.screens.bottombar.search.domain

import com.nyth.app.core.designsystem.platform.viewmodel.BaseViewState
import com.nyth.app.core.model.local.enums.UiState

data class SearchScreenState(
    val errorMessage: String? = null,
    override val uiState: UiState = UiState.LOADING
) : BaseViewState