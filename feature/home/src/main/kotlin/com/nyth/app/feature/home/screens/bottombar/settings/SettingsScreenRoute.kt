package com.nyth.app.feature.home.screens.bottombar.settings

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.nyth.app.core.designsystem.components.CustomButton
import com.nyth.app.core.designsystem.navigation.Screen

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
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Settings", style = androidx.compose.material3.MaterialTheme.typography.headlineMedium)
        androidx.compose.material3.Divider(modifier = Modifier.padding(vertical = androidx.compose.ui.unit.dp(16)))
        Text("Support", style = androidx.compose.material3.MaterialTheme.typography.titleMedium)
        Text(
            "If you have any issues or need help, please contact our support team. We are here to assist you!",
            modifier = Modifier.padding(vertical = androidx.compose.ui.unit.dp(8)),
            style = androidx.compose.material3.MaterialTheme.typography.bodyMedium
        )
        androidx.compose.material3.Divider(modifier = Modifier.padding(vertical = androidx.compose.ui.unit.dp(16)))
        Text("Contact Us", style = androidx.compose.material3.MaterialTheme.typography.titleMedium)
        Text(
            "Email: support@parkit.com",
            modifier = Modifier.padding(vertical = androidx.compose.ui.unit.dp(4)),
            style = androidx.compose.material3.MaterialTheme.typography.bodyMedium
        )
        CustomButton(text = "Send Email", onClick = {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:support@parkit.com")
                putExtra(Intent.EXTRA_SUBJECT, "Support Request")
            }
            context.startActivity(intent)
        })
        androidx.compose.material3.Divider(modifier = Modifier.padding(vertical = androidx.compose.ui.unit.dp(16)))
        CustomButton(text = "Logout", onClick = logoutUser)
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewScreen() {
    SettingsScreen(onBack = {}, navToNext = {}, logoutUser = {})
}
