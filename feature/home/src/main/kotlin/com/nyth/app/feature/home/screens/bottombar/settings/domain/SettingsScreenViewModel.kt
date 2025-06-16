package com.nyth.app.feature.home.screens.bottombar.settings.domain

import androidx.lifecycle.viewModelScope
import com.nyth.app.core.database.sharedpref.SharedPreferenceManager
import com.nyth.app.core.designsystem.platform.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsScreenViewModel @Inject constructor(
    private val sharedPref: SharedPreferenceManager
) : BaseViewModel<SettingsScreenState, SettingsScreenAction>(SettingsScreenState()) {

    init {
        updateState(viewState = state.copy(selectedCity = sharedPref.selectedCity ?: "istanbul"))

        viewModelScope.launch(Dispatchers.IO) {
            sharedPref["exampleKey"] = "denemeString"
            sharedPref["exampleKey2"] = 12544
            sharedPref["exampleKey3"] = 1254454L
            sharedPref["exampleKey4"] = false
        }
    }

    fun changeSelectedCity(selectedCity: String) {
        sharedPref.selectedCity = selectedCity
        updateState(viewState = state.copy(selectedCity = selectedCity))
    }

    override fun onReduceState(viewAction: SettingsScreenAction): SettingsScreenState? {
        return null
    }
}