package com.nyth.app.feature.home.screens.bottombar.qibla.domain

import com.nyth.app.core.designsystem.platform.viewmodel.BaseViewModel
import com.nyth.app.core.network.repository.PrayRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QiblaScreenViewModel @Inject constructor(
    private val prayRepository: PrayRepository,
) : BaseViewModel<QiblaScreenState, QiblaScreenAction>(QiblaScreenState()) {

    override fun onReduceState(viewAction: QiblaScreenAction): QiblaScreenState? {
        return null
    }
}