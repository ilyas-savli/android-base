package com.nyth.app.feature.auth.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.nyth.app.core.designsystem.R
import com.nyth.app.core.designsystem.components.CustomCircularProgress
import com.nyth.app.core.designsystem.theme.customColorsPalette
import com.nyth.app.core.designsystem.theme.typographyNunito
import com.nyth.app.core.model.local.Screen
import com.nyth.app.feature.auth.screens.splash.domain.SplashScreenAction
import com.nyth.app.feature.auth.screens.splash.domain.SplashScreenState
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
    state: SplashScreenState,
    onEvent: (SplashScreenAction) -> Unit
) {
    LaunchedEffect(key1 = state) {
        delay(1500)
        navController.navigate(Screen.DashboardScreen) {
            navController.graph.startDestinationRoute?.let { screenRoute ->
                popUpTo(screenRoute) {
                    saveState = true
                }
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(color = Color.Black)
    }
    Column(
        Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.customColorsPalette.white),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(290.dp),
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = stringResource(
                id = R.string.default_content_description
            ),
            contentScale = ContentScale.Crop
        )
        Text(text = stringResource(id = R.string.app_name), style = typographyNunito.h2Bold)
        Spacer(modifier = Modifier.size(200.dp))
        CustomCircularProgress()
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ScreenPreview() {
    val navController = rememberNavController()
    SplashScreen(
        navController = navController,
        state = SplashScreenState(),
        onEvent = {},
    )
}