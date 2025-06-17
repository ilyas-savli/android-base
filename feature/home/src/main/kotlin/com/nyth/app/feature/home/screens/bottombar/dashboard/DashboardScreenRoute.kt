package com.nyth.app.feature.home.screens.bottombar.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.nyth.app.core.designsystem.navigation.Screen
import com.nyth.app.feature.home.screens.bottombar.dashboard.domain.DashboardScreenViewModel

@Composable
fun DashboardScreenRoute(
    onBack: () -> Unit,
    navToNext: (Screen) -> Unit
) {
    val viewModel: DashboardScreenViewModel = hiltViewModel()

    DashboardScreen(
        onBack = onBack,
        navToNext = navToNext
    )
}

@Composable
private fun DashboardScreen(
    onBack: () -> Unit,
    navToNext: (Screen) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Dashboard")
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewScreen() {
    DashboardScreen(onBack = {}, navToNext = {})
}