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
import com.nyth.app.core.designsystem.components.CustomButton
import com.nyth.app.core.designsystem.navigation.Screen
import com.nyth.app.feature.home.screens.bottombar.settings.domain.SettingsScreenViewModel

@Composable
fun SettingsScreenRoute(
    onBack: () -> Unit, navToNext: (Screen) -> Unit, popUntil: (Screen) -> Unit
) {
    val viewModel: SettingsScreenViewModel = hiltViewModel()

    SettingsScreen(
        onBack = onBack, navToNext = navToNext, logoutUser = {
            viewModel.logoutUser()
            popUntil(Screen.Login)
        }
    )
}

@Composable
private fun SettingsScreen(
    onBack: () -> Unit,
    navToNext: (Screen) -> Unit,
    logoutUser: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Settings")
        CustomButton(text = "Logout", onClick = logoutUser)
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewScreen() {
    SettingsScreen(onBack = {}, navToNext = {}, logoutUser = {})
}