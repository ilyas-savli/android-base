package com.nyth.app.main.graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.nyth.app.core.designsystem.platform.navigation.Screen
import com.nyth.app.feature.auth.screens.splash.SplashScreenRoot
import com.nyth.app.feature.auth.screens.splash.domain.SplashScreenViewModel

@Composable
fun RootGraph() {
    val backstack = remember { mutableStateListOf<Screen>(Screen.Splash) }

    NavDisplay(
        backStack = backstack, onBack = {
            backstack.removeLastOrNull()
        }, entryDecorators = listOf(
            rememberSavedStateNavEntryDecorator(), rememberViewModelStoreNavEntryDecorator()
        ), entryProvider = entryProvider {
            entry<Screen.Splash> {
                val viewModel = hiltViewModel<SplashScreenViewModel>()
                SplashScreenRoot(
                    navNext = {
                        backstack.add(Screen.NestedGraph)
                    },
                    state = viewModel.uiState.collectAsStateWithLifecycle().value,
                    onEvent = viewModel::sendAction
                )
            }

            entry<Screen.NestedGraph> {
                NestedGraph()
            }
        })
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewScreen() {
    RootGraph()
}