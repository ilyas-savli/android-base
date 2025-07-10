package com.nyth.app.main.graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.nyth.app.core.designsystem.ext.popUntil
import com.nyth.app.core.designsystem.navigation.Screen
import com.nyth.app.feature.auth.screens.login.LoginScreenRoute
import com.nyth.app.feature.auth.screens.splash.SplashScreenRoute
import com.nyth.app.main.domain.MainScreenViewModel
import kotlinx.coroutines.launch

@Composable
fun RootGraph() {
    val viewModelMain: MainScreenViewModel = hiltViewModel()

    val backstack = remember { mutableStateListOf<Screen>(Screen.Splash) }

    val coroutine = rememberCoroutineScope()

    NavDisplay(
        backStack = backstack, onBack = {
            backstack.removeLastOrNull()
        }, entryDecorators = listOf(
            rememberSavedStateNavEntryDecorator(), rememberViewModelStoreNavEntryDecorator()
        ), entryProvider = entryProvider {
            entry<Screen.Splash> {
                SplashScreenRoute(
                    navNext = {
                        backstack.add(it)
                    })
            }

            entry<Screen.Login> {
                LoginScreenRoute(
                    onBack = {
                        backstack.removeLastOrNull()
                    },
                    navNext = {
                        backstack.add(it)
                    })
            }

            entry<Screen.NestedGraph> {
                NestedGraph(navToNext = {
                    backstack.add(it)
                }, popUntil = { popScreen ->
                    coroutine.launch {
                        backstack.popUntil(popScreen)
                    }
                })
            }
        })
}

@Preview(showBackground = true)
@Composable
private fun PreviewScreen() {
    RootGraph()
}