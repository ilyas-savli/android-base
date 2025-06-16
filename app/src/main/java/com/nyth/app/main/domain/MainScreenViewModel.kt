package com.nyth.app.main.domain

import androidx.compose.runtime.mutableStateListOf
import com.nyth.app.core.designsystem.platform.navigation.Screen
import com.nyth.app.core.designsystem.platform.viewmodel.BaseViewModel
import com.nyth.app.core.model.local.enums.UiState
import com.nyth.app.core.network.utils.AuthManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val authManager: AuthManager,
) : BaseViewModel<MainScreenState, MainScreenAction>(
    MainScreenState()
) {
    val backStack = mutableStateListOf<Screen>(Screen.Splash)

    override fun onReduceState(viewAction: MainScreenAction): MainScreenState? =
        when (viewAction) {
            is MainScreenAction.OnFailure -> {
                state.copy(
                    uiState = UiState.ERROR,
                    errorMessage = viewAction.errorMessage
                )
            }

            MainScreenAction.OnLoading -> state.copy(
                uiState = UiState.LOADING, errorMessage = null
            )

            is MainScreenAction.OnSuccess -> state.copy(
                errorMessage = null,
                uiState = UiState.SUCCESS,
                isUserLoggedIn = viewAction.isUserLoggedIn
            )
        }
}
