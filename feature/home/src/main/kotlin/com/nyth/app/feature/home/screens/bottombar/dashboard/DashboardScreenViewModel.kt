package com.nyth.app.feature.home.screens.bottombar.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyth.app.core.model.remote.network.NetworkResult
import com.nyth.app.core.network.repository.UserRepository
import com.nyth.app.core.network.utils.AuthManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardScreenViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val authManager: AuthManager
) : ViewModel() {
    val dashboardUiState: StateFlow<DashboardUiState> = dashboardUiState()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = DashboardUiState.Loading,
        )

    init {
        viewModelScope.launch {
            userRepository.getPrayTimes(key = "sefef", city = "istanbul")
        }
    }

    val shouldLogoutUser = channelFlow {
        send(authManager.isUserLoggedIn())
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = false,
    )

    private fun dashboardUiState(): Flow<DashboardUiState> = channelFlow {
        val prayTimesResponse =
            userRepository.getPrayTimes(key = "sefef", city = "istanbul")

        combine(
            shouldLogoutUser,
            prayTimesResponse
        ) { userLoggedIn, prayTimes ->
            when (prayTimes) {
                is NetworkResult.Success -> {
                    if (!userLoggedIn) {
                        DashboardUiState.Error
                    } else {
                        DashboardUiState.Success(prayTimeResponse = prayTimes.data)
                    }
                }

                is NetworkResult.Loading -> DashboardUiState.Loading
                is NetworkResult.Error -> DashboardUiState.Error
            }
        }.collectLatest { uiState ->
            send(uiState)
        }
    }
}
