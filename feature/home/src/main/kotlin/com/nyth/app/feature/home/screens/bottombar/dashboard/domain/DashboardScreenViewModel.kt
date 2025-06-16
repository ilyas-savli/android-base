package com.nyth.app.feature.home.screens.bottombar.dashboard.domain

import com.nyth.app.core.database.sharedpref.SharedPreferenceManager
import com.nyth.app.core.designsystem.platform.viewmodel.BaseViewModel
import com.nyth.app.core.network.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardScreenViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val sharedPref: SharedPreferenceManager
) : BaseViewModel<DashboardScreenState, DashboardScreenAction>(DashboardScreenState()) {

    override fun onReduceState(viewAction: DashboardScreenAction): DashboardScreenState? {
        return null
    }
}
