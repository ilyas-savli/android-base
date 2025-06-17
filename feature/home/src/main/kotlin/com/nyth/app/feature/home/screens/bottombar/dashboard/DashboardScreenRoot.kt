package com.nyth.app.feature.home.screens.bottombar.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.nyth.app.feature.home.screens.bottombar.dashboard.domain.DashboardScreenViewModel

@Composable
fun DashboardScreenRoot() {
    val viewModel: DashboardScreenViewModel = hiltViewModel()

    DashboardScreen(
        viewModel = viewModel
    )
}

@Composable
private fun DashboardScreen(
    viewModel: DashboardScreenViewModel
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Dashboard")
    }
}