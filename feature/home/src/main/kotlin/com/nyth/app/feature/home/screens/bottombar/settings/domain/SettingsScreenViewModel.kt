package com.nyth.app.feature.home.screens.bottombar.settings.domain

import androidx.lifecycle.ViewModel
import com.nyth.app.core.database.sharedpref.SharedPreferenceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsScreenViewModel @Inject constructor(
    private val sharedPref: SharedPreferenceManager
) : ViewModel() {

    fun changeSelectedCity(selectedCity: String) {
        sharedPref.selectedCity = selectedCity
    }
}