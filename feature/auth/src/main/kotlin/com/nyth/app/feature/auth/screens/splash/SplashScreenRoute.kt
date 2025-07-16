package com.nyth.app.feature.auth.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.nyth.app.core.designsystem.R
import com.nyth.app.core.designsystem.components.CustomCircularProgress
import com.nyth.app.core.designsystem.navigation.Screen
import com.nyth.app.core.designsystem.theme.LocalColorsPalette
import com.nyth.app.core.designsystem.theme.StablexTypography

@Composable
fun SplashScreenRoute(
    navNext: (Screen) -> Unit
) {
    val viewModel: SplashScreenViewModel = hiltViewModel()
    val splashUiState by viewModel.splashUiState.collectAsStateWithLifecycle()

    SplashScreen(navNext = navNext, splashUiState = splashUiState)
}

@Composable
private fun SplashScreen(
    navNext: (Screen) -> Unit,
    splashUiState: SplashUiState
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(splashUiState) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                if (splashUiState is SplashUiState.Success) {
                    if (splashUiState.isLoggedIn) {
                        navNext(Screen.NestedGraph)
                    } else {
                        navNext(Screen.Login)
                    }
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(color = Color.Black)
    }
    Column(
        Modifier
            .fillMaxSize()
            .background(color = LocalColorsPalette.current.white),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(290.dp),
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = stringResource(
                id = R.string.default_content_description
            ),
            contentScale = ContentScale.Crop
        )
        Text(text = stringResource(id = R.string.app_name), style = StablexTypography.mulish400)
        Spacer(modifier = Modifier.size(200.dp))

        if (splashUiState == SplashUiState.Loading) {
            CustomCircularProgress()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ScreenPreview() {
    SplashScreen(
        navNext = {}, splashUiState = SplashUiState.Loading
    )
}