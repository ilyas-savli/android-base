package com.nyth.app.main.domain

import androidx.lifecycle.viewModelScope
import com.nyth.app.core.database.EncryptedDataStoreManager
import com.nyth.app.core.designsystem.platform.viewmodel.BaseViewModel
import com.nyth.app.core.model.local.UserModel
import com.nyth.app.core.model.local.enums.UiState
import com.nyth.app.core.model.remote.network.Status
import com.nyth.app.core.model.remote.response.AccountResponse
import com.nyth.app.core.network.repository.IdentityRepository
import com.nyth.app.core.network.utils.AuthManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val authManager: AuthManager,
    private val dataStore: EncryptedDataStoreManager,
    private val identityRepository: IdentityRepository
) : BaseViewModel<MainScreenState, MainScreenAction>(
    MainScreenState()
) {
    private fun getIfUserLoggedIn() {
        viewModelScope.launch {
            dataStore.tokenResponse.collectLatest {
                if (it != null) {
                    getUserInfo()
                }
                sendAction(viewAction = MainScreenAction.OnSuccess(isUserLoggedIn = it != null))
            }
        }
    }

    private fun setUserModel(accountModel: AccountResponse) {
        viewModelScope.launch(Dispatchers.IO) {
            val account = UserModel(
                userId = accountModel.id,
                firstName = accountModel.firstName,
                lastName = accountModel.lastName,
                email = accountModel.email,
                type = accountModel.accountType,
                profileImageUrl = accountModel.profileImageUrl
            )
            dataStore.userModel = flowOf(account)
        }
    }

    private fun getUserInfo() {
        viewModelScope.launch {
            val res = identityRepository.getProfile().first()
            when (res.status) {
                Status.SUCCESS -> {
                    res.data?.data?.let {
                        setUserModel(it)
                    }
                }

                Status.ERROR -> updateState(
                    state.copy(
                        uiState = UiState.ERROR,
                        errorMessage = res.error.toString()
                    )
                )

            }
        }
    }

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
                getIfUserLoggedIn()
                null
            }

            MainScreenAction.ListenOnErrors -> {
                state.copy(errorMessage = "Deneme")
            }
        }
}
