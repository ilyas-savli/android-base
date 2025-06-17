package com.nyth.app.feature.auth.screens.login

import androidx.compose.foundation.background
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
import com.nyth.app.core.designsystem.theme.LocalColorsPalette

@Composable
fun LoginScreenRoute(
    onBack: () -> Unit,
    navNext: (Screen) -> Unit
) {
    val viewModel = hiltViewModel<LoginScreenViewModel>()
    LoginScreen(onBack = onBack, navNext = navNext, loginUser = {
        viewModel.loginUser()
        navNext(Screen.NestedGraph)
    })
}

@Composable
private fun LoginScreen(
    onBack: () -> Unit, navNext: (Screen) -> Unit, loginUser: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(color = LocalColorsPalette.current.white),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Login")
        CustomButton(text = "Login", onClick = loginUser)
    }
}

@Preview(showBackground = true)
@Composable
private fun ScreenPreview() {
    LoginScreen(
        onBack = {},
        navNext = {},
        loginUser = {}
    )
}