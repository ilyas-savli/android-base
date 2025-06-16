package com.nyth.app.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nyth.app.core.designsystem.theme.StablexTheme
import com.nyth.app.main.domain.MainScreenState
import com.nyth.app.main.domain.MainScreenViewModel
import com.nyth.app.main.graph.RootGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {
            navController = rememberNavController()
            StablexTheme {
                val viewModel: MainScreenViewModel = hiltViewModel()
                val state: State<MainScreenState> = viewModel.uiState.collectAsStateWithLifecycle()

                LaunchedEffect(Unit) {
                    handleDeepLink(intent)
                }

                RootGraph()
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleDeepLink(intent)
    }

    private fun handleDeepLink(intent: Intent?) {
        if (Intent.ACTION_VIEW == intent?.action) {
            intent.data?.let { uri ->
                navController.navigate(uri)
            }
        }
    }
}