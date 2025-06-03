package com.nyth.app

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nyth.app.core.designsystem.theme.AppDefaultTheme
import com.nyth.app.main.MainScreen
import com.nyth.app.main.domain.MainScreenState
import com.nyth.app.main.domain.MainScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()

        super.onCreate(savedInstanceState)

        installSplashScreen()

        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            navController = rememberNavController()
            AppDefaultTheme(
                darkTheme = false
            ) {
                val viewModel: MainScreenViewModel = hiltViewModel()
                val state: State<MainScreenState> = viewModel.uiState.collectAsStateWithLifecycle()

                LaunchedEffect(Unit) {
                    handleDeepLink(intent)
                }

                MainScreen(
                    navController = navController,
                    state = state.value,
                    onEvent = viewModel::sendAction,
                )
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