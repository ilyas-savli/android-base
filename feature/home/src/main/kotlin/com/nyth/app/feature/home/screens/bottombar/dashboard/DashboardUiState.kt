package com.nyth.app.feature.home.screens.bottombar.dashboard

import com.nyth.app.core.model.remote.response.pray.PrayTimeResponse

sealed interface DashboardUiState {
    data object Loading : DashboardUiState

    data object Error : DashboardUiState

    data class Success(
        val prayTimeResponse: PrayTimeResponse
    ) : DashboardUiState
}
