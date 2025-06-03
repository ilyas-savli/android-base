package com.nyth.app.main.domain

import com.nyth.app.core.database.EncryptedDataStoreManager
import com.nyth.app.core.designsystem.platform.viewmodel.BaseViewModel
import com.nyth.app.core.model.local.enums.UiState
import com.nyth.app.core.network.utils.AuthManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val authManager: AuthManager,
    private val dataStore: EncryptedDataStoreManager
) : BaseViewModel<MainScreenState, MainScreenAction>(
    MainScreenState()
) {
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

            MainScreenAction.GetUserInfo -> {
                null
            }

            MainScreenAction.ListenOnErrors -> {
                state.copy(errorMessage = "Deneme")
            }
        }
}
