package com.nyth.app.feature.home.screens.bottombar.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nyth.app.core.designsystem.platform.navigation.Screen
import com.nyth.app.feature.home.screens.bottombar.settings.domain.SettingsScreenState
import com.nyth.app.feature.home.screens.bottombar.settings.domain.SettingsScreenViewModel

@Composable
fun SettingsScreenRoot(onBack: () -> Unit = {}, navToNext: (Screen) -> Unit = {}) {
    val viewModel: SettingsScreenViewModel = hiltViewModel()
    val state = viewModel.uiState.collectAsStateWithLifecycle().value

    SettingsScreen(
        state = state,
        onChangeSelectedCity = { selectedCity ->
            viewModel.changeSelectedCity(selectedCity = selectedCity)
        },
        onBack = onBack,
        navToNext = navToNext
    )
}

@Composable
private fun SettingsScreen(
    state: SettingsScreenState,
    onChangeSelectedCity: (String) -> Unit = {},
    onBack: () -> Unit = {},
    navToNext: (Screen) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Settings")
    }
}

@Preview
@Composable
private fun PreviewScreen() {
    val state = SettingsScreenState()
    SettingsScreen(state = state)
}