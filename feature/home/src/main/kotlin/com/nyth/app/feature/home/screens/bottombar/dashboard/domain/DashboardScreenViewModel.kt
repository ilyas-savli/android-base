package com.nyth.app.feature.home.screens.bottombar.dashboard.domain

import androidx.lifecycle.ViewModel
import com.nyth.app.core.database.sharedpref.SharedPreferenceManager
import com.nyth.app.core.network.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardScreenViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val sharedPref: SharedPreferenceManager
) : ViewModel() {
}
