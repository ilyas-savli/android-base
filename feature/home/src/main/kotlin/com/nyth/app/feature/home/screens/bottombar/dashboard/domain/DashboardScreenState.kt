package com.nyth.app.feature.home.screens.bottombar.dashboard.domain

import com.nyth.app.core.designsystem.platform.viewmodel.BaseViewState
import com.nyth.app.core.model.local.enums.UiState
import com.nyth.app.core.model.remote.response.pray.PrayTimeUiModel

data class DashboardScreenState(
    val errorMessage: String? = null,
    val prayTimes: PrayTimeUiModel? = null,
    val countDown: String? = null,
    val date: String? = null,
    val hours: Int = 0,
    val minutes: Int = 0,
    val seconds: Int = 0,
    val nextPrayTimeName: String = "",
    override val uiState: UiState = UiState.LOADING,
) : BaseViewState
