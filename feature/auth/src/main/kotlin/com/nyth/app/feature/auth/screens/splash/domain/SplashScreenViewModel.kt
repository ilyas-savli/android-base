package com.nyth.app.feature.auth.screens.splash.domain

import androidx.lifecycle.viewModelScope
import com.nyth.app.core.database.EncryptedDataStoreManager
import com.nyth.app.core.designsystem.platform.viewmodel.BaseViewModel
import com.nyth.app.core.model.local.enums.UiState
import com.nyth.app.core.network.utils.AuthManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val dataStore: EncryptedDataStoreManager,
    private val authManager: AuthManager
) : BaseViewModel<SplashScreenState, SplashScreenAction>(SplashScreenState()) {

    private fun checkUserLoggedIn() {
        viewModelScope.launch {
            authManager.currentUser?.let {
                sendAction(viewAction = SplashScreenAction.NavigateToListing)
            } ?: run {
                sendAction(viewAction = SplashScreenAction.NavigateToLogin)
            }
        }
    }

    override fun onReduceState(viewAction: SplashScreenAction): SplashScreenState? =
        when (viewAction) {
            is SplashScreenAction.OnFailure -> {
                Timber.d(
                    "GeneralLog3 -> ${
                        viewAction.errorMessage
                    }"
                )
                state.copy(
                    uiState = UiState.ERROR,
                    errorMessage = viewAction.errorMessage
                )
            }

            SplashScreenAction.OnLoading -> state.copy(
                uiState = UiState.LOADING, errorMessage = null
            )

            SplashScreenAction.CheckUserLoggedIn -> {
                checkUserLoggedIn()
                null
            }

            SplashScreenAction.NavigateToListing -> state.copy(
                uiState = UiState.SUCCESS,
                isNavigateToHome = true
            )

            SplashScreenAction.NavigateToLogin -> state.copy(
                uiState = UiState.SUCCESS,
                isNavigateToLogin = true
            )
        }
}
