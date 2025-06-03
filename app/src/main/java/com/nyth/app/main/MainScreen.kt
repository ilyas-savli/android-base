package com.nyth.app.main

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nyth.app.core.designsystem.R
import com.nyth.app.core.designsystem.components.base.BottomNavigationItems
import com.nyth.app.core.designsystem.theme.customColorsPalette
import com.nyth.app.core.designsystem.theme.typographyNunito
import com.nyth.app.core.model.ext.CollectionExt.safeGet
import com.nyth.app.core.model.ext.StringExt.getScreen
import com.nyth.app.core.model.local.BottomNavigationItem
import com.nyth.app.core.model.local.Screen
import com.nyth.app.feature.auth.screens.splash.SplashScreen
import com.nyth.app.feature.auth.screens.splash.domain.SplashScreenViewModel
import com.nyth.app.feature.home.screens.bottombar.dashboard.DashboardScreen
import com.nyth.app.feature.home.screens.bottombar.qibla.QiblaScreen
import com.nyth.app.feature.home.screens.bottombar.settings.SettingsScreen
import com.nyth.app.feature.home.screens.privacy.PrivacyPolicyScreen
import com.nyth.app.main.domain.MainScreenAction
import com.nyth.app.main.domain.MainScreenState
import timber.log.Timber

@Composable
fun MainScreen(
    navController: NavHostController,
    state: MainScreenState,
    onEvent: (MainScreenAction) -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        val navigationItems = remember {
            mutableStateOf(BottomNavigationItems.LISTING)
        }
        val currentDestination: MutableState<Screen> = remember {
            mutableStateOf(Screen.DashboardScreen)
        }

        val navBackStackEntry by navController.currentBackStackEntryAsState()

        currentDestination.value =
            navBackStackEntry?.destination?.route?.getScreen() ?: Screen.AuthSplash

        var selectedNavItem: BottomNavigationItem? by remember {
            mutableStateOf(
                BottomNavigationItems.LISTING.navigationItems.safeGet(index = 0)
            )
        }

        Timber.d("Current Screen -> ${currentDestination.value}")

        Scaffold(
            modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars),
            topBar = {},
            bottomBar = {
                NavigationBar(
                    containerColor = MaterialTheme.customColorsPalette.bottomBar,
                ) {
                    navigationItems.value.navigationItems.forEachIndexed { index, item ->
                        NavigationBarItem(
                            colors = NavigationBarItemColors(
                                selectedIconColor = MaterialTheme.customColorsPalette.white,
                                selectedTextColor = MaterialTheme.customColorsPalette.white,
                                selectedIndicatorColor = Color.Unspecified,
                                unselectedIconColor = MaterialTheme.customColorsPalette.bottomBarUnselected,
                                unselectedTextColor = MaterialTheme.customColorsPalette.bottomBarUnselected,
                                disabledIconColor = Color.Unspecified,
                                disabledTextColor = Color.Unspecified
                            ),
                            selected = selectedNavItem?.route == item.route,
                            onClick = {
                                if (selectedNavItem?.route != item.route) {
                                    navController.navigate(route = item.route) {
                                        popUpTo(item.route) {
                                            saveState = false
                                        }
                                        // Avoid multiple copies of the same destination when
                                        // ReSelecting the same item
                                        launchSingleTop = true
                                        // Restore state when ReSelecting a previously selected item
                                        restoreState = false
                                    }
                                    selectedNavItem = item
                                    return@NavigationBarItem
                                }
                            }, label = {
                                Text(
                                    text = stringResource(id = item.labelRes),
                                    style = typographyNunito.regularNeutral500S11H13.copy(fontWeight = FontWeight.Medium),
                                    color = if (selectedNavItem?.route == item.route) MaterialTheme.customColorsPalette.secondary100 else Color.Unspecified
                                )
                            }, alwaysShowLabel = true, icon = {
                                Icon(
                                    painter = painterResource(id = item.iconRes),
                                    contentDescription = stringResource(id = R.string.default_content_description)
                                )
                            }
                        )
                    }
                }
            }
        ) { paddingValues ->
            NavHost(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                navController = navController,
                startDestination = Screen.AuthSplash,
                enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
                exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) },
                popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }) },
                popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) }) {
                // auth
                composable<Screen.AuthSplash> {
                    val viewModel: SplashScreenViewModel = hiltViewModel()
                    SplashScreen(
                        navController = navController,
                        state = viewModel.uiState.collectAsStateWithLifecycle().value,
                        onEvent = viewModel::sendAction
                    )
                }
                composable<Screen.DashboardScreen> {
                    DashboardScreen(
                        navController = navController,
                    )
                }
                composable<Screen.Qibla> {
                    QiblaScreen()
                }
                composable<Screen.Settings> {
                    SettingsScreen(onBack = {
                        navController.navigate(Screen.DashboardScreen)
                        selectedNavItem =
                            BottomNavigationItems.LISTING.navigationItems.safeGet(index = 0)
                    }, navToNext = {
                        navController.navigate(it)
                    })
                }
                composable<Screen.PrivacyPolicy> {
                    PrivacyPolicyScreen(onBack = {
                        navController.popBackStack()
                    })
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewScreen() {
    val navController = rememberNavController()

    MainScreen(navController = navController, state = MainScreenState(), onEvent = {})
}