package com.nyth.app.feature.home.screens.bottombar.settings

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
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
        Text("Settings", style = MaterialTheme.typography.titleLarge)
        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
        Text("Support", style = MaterialTheme.typography.titleMedium)
        Text(
            "If you have any issues or need help, please contact our support team. We are here to assist you!",
            modifier = Modifier.padding(vertical = 8.dp),
            style = MaterialTheme.typography.bodyLarge
        )
        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
        Text("Contact Us", style = MaterialTheme.typography.titleMedium)
        Text(
            "Email: support@parkit.com",
            modifier = Modifier.padding(vertical = 4.dp),
            style = MaterialTheme.typography.bodyLarge
        )
        CustomButton(text = "Send Email", onClick = {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = "mailto:support@androidbase.com".toUri()
                putExtra(Intent.EXTRA_SUBJECT, "Support Request")
            }
            context.startActivity(intent)
        })
        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
        CustomButton(text = "Logout", onClick = logoutUser)
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewScreen() {
    SettingsScreen(onBack = {}, navToNext = {}, logoutUser = {})
}