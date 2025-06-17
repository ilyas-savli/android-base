package com.nyth.app.feature.home.screens.bottombar.search.domain

import androidx.lifecycle.ViewModel
import com.nyth.app.core.network.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {
}