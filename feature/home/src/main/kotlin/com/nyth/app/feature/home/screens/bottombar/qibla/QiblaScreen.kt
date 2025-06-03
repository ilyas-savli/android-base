package com.nyth.app.feature.home.screens.bottombar.qibla

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nyth.app.core.designsystem.theme.LocalCustomColorsPalette
import com.nyth.app.feature.home.screens.bottombar.qibla.domain.QiblaScreenState
import com.nyth.app.feature.home.screens.bottombar.qibla.domain.QiblaScreenViewModel

@Composable
fun QiblaScreen() {
    val viewModel: QiblaScreenViewModel = hiltViewModel()
    val state = viewModel.uiState.collectAsStateWithLifecycle().value

    QiblaScreen(
        viewModel = viewModel, state = state
    )
}

@Composable
private fun QiblaScreen(
    viewModel: QiblaScreenViewModel, state: QiblaScreenState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                LocalCustomColorsPalette.current.bgGreen
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Qibla Screen", color = LocalCustomColorsPalette.current.white)
    }
}