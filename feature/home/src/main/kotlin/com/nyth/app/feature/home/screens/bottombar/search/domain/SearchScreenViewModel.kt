package com.nyth.app.feature.home.screens.bottombar.search.domain

import com.nyth.app.core.designsystem.platform.viewmodel.BaseViewModel
import com.nyth.app.core.network.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : BaseViewModel<SearchScreenState, SearchScreenAction>(SearchScreenState()) {

    override fun onReduceState(viewAction: SearchScreenAction): SearchScreenState? {
        return null
    }
}