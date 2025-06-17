package com.nyth.app.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nyth.app.core.designsystem.theme.StablexTheme
import com.nyth.app.main.graph.RootGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        enableEdgeToEdge()

        // Extend into system bars
        WindowCompat.setDecorFitsSystemWindows(window, false)
        
        setContent {
            navController = rememberNavController()
            StablexTheme {
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