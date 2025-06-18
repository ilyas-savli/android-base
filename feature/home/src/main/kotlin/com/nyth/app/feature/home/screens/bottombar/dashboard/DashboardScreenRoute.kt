package com.nyth.app.feature.home.screens.bottombar.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nyth.app.core.designsystem.components.CustomCircularProgress
import com.nyth.app.core.designsystem.navigation.Screen

@Composable
fun DashboardScreenRoute(
    onBack: () -> Unit,
    navToNext: (Screen) -> Unit,
    popUntil: (Screen) -> Unit
) {
    val viewModel: DashboardScreenViewModel = hiltViewModel()
    val dashboardUiState by viewModel.dashboardUiState.collectAsStateWithLifecycle()
    val shouldLogoutUser by viewModel.shouldLogoutUser.collectAsStateWithLifecycle()

    DashboardScreen(
        onBack = onBack,
        navToNext = navToNext,
        popUntil = popUntil,
        dashboardUiState = dashboardUiState,
        shouldLogoutUser = shouldLogoutUser
    )
}

@Composable
private fun DashboardScreen(
    onBack: () -> Unit,
    navToNext: (Screen) -> Unit,
    dashboardUiState: DashboardUiState,
    shouldLogoutUser: Boolean,
    popUntil: (Screen) -> Unit
) {
    LaunchedEffect(shouldLogoutUser) {
        if (shouldLogoutUser) {
            popUntil(Screen.Login)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (dashboardUiState) {
            DashboardUiState.Error -> Text(text = "Error")
            DashboardUiState.Loading -> CustomCircularProgress()
            is DashboardUiState.Success -> {
                Text(text = "Pray Times Fetch Success!")
                Text(text = dashboardUiState.prayTimeResponse.toString())
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewScreen() {
    DashboardScreen(
        onBack = {}, navToNext = {}, popUntil = {},
        dashboardUiState = DashboardUiState.Loading,
        shouldLogoutUser = false
    )
}