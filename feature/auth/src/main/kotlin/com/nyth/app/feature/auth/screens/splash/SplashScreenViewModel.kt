package com.nyth.app.feature.auth.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyth.app.core.network.utils.AuthManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val authManager: AuthManager
) : ViewModel() {
    private fun isUserLoggedIn(): Flow<Boolean> = channelFlow {
        delay(3000)
        send(authManager.isUserLoggedIn())
    }

    private fun needForceUpdate(): Flow<Boolean> = channelFlow {
        send(false)
    }

    val splashUiState: StateFlow<SplashUiState> =
        combine(
            isUserLoggedIn(),
            needForceUpdate()
        ) { isUserLoggedIn, needForceUpdate ->
            if (needForceUpdate) {
                SplashUiState.NeedForceUpdate
            } else {
                SplashUiState.Success(isLoggedIn = isUserLoggedIn)
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = SplashUiState.Loading
        )
}