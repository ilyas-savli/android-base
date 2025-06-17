package com.nyth.app.main.graph

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.nyth.app.core.designsystem.R
import com.nyth.app.core.designsystem.navigation.BottomBarScreen
import com.nyth.app.core.designsystem.navigation.BottomBarScreenSaver
import com.nyth.app.core.designsystem.navigation.Screen
import com.nyth.app.core.designsystem.navigation.bottomBarItems
import com.nyth.app.feature.home.screens.bottombar.dashboard.DashboardScreenRoute
import com.nyth.app.feature.home.screens.bottombar.search.SearchScreenRoute
import com.nyth.app.feature.home.screens.bottombar.settings.SettingsScreenRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NestedGraph(navToNext: (Screen) -> Unit, popUntil: (Screen) -> Unit) {
    val backstack = remember { mutableStateListOf<BottomBarScreen>(BottomBarScreen.Home) }

    var currentBottomBarScreen: BottomBarScreen by rememberSaveable(stateSaver = BottomBarScreenSaver) {
        mutableStateOf(
            BottomBarScreen.Home
        )
    }

    val stateHolder = rememberSaveableStateHolder()

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.statusBarsPadding(),
                title = { Text(text = "TopAppBar Title") },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            painterResource(id = R.drawable.ic_search),
                            contentDescription = null
                        )
                    }
                })
        }, bottomBar = {
            NavigationBar(modifier = Modifier.navigationBarsPadding()) {
                bottomBarItems.forEach { destination ->
                    NavigationBarItem(
                        selected = currentBottomBarScreen == destination,
                        onClick = {
                            val last = backstack.lastOrNull()
                            if (last != destination) {
                                if (last in bottomBarItems) {
                                    backstack.remove(last)
                                }
                            }
                            backstack.add(destination)
                            currentBottomBarScreen = destination
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = destination.icon),
                                contentDescription = stringResource(id = destination.title)
                            )
                        }
                    )
                }
            }
        }) {
        NavDisplay(
            modifier = Modifier
                .padding(it),
            backStack = backstack, onBack = {
                backstack.removeLastOrNull()
            }, entryDecorators = listOf(
                rememberSavedStateNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ), entryProvider = entryProvider {
                entry<BottomBarScreen.Home> {
                    DashboardScreenRoute(onBack = {
                        backstack.removeLastOrNull()
                    }, navToNext = navToNext)
                }
                entry<BottomBarScreen.Search> {
                    // To preserve the state of the composable (should use rememberSaveable for variables)
                    stateHolder.SaveableStateProvider(key = "Search") {
                        SearchScreenRoute(onBack = {
                            backstack.removeLastOrNull()
                        }, navToNext = navToNext)
                    }
                }
                entry<BottomBarScreen.Settings> {
                    SettingsScreenRoute(onBack = {
                        backstack.removeLastOrNull()
                    }, navToNext = navToNext, popUntil = popUntil)
                }
            })
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewScreen() {
    RootGraph()
}