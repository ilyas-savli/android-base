package com.nyth.app.core.designsystem.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.saveable.Saver
import androidx.navigation3.runtime.NavKey
import com.nyth.app.core.designsystem.R
import kotlinx.serialization.Serializable

val bottomBarItems = listOf(
    BottomBarScreen.Home, BottomBarScreen.Search, BottomBarScreen.Settings
)

@Serializable
sealed class BottomBarScreen(
    @DrawableRes val icon: Int, @StringRes val title: Int
) : NavKey {
    @Serializable
    data object Home : BottomBarScreen(R.drawable.ic_home, R.string.home_page)

    @Serializable
    data object Search : BottomBarScreen(R.drawable.ic_search, R.string.search)

    @Serializable
    data object Settings : BottomBarScreen(R.drawable.ic_settings, R.string.settings)
}

val BottomBarScreenSaver =
    Saver<BottomBarScreen, String>(save = { it::class.simpleName ?: "Unknown" }, restore = {
        when (it) {
            BottomBarScreen.Home::class.simpleName -> BottomBarScreen.Home
            BottomBarScreen.Search::class.simpleName -> BottomBarScreen.Search
            BottomBarScreen.Settings::class.simpleName -> BottomBarScreen.Settings
            else -> BottomBarScreen.Home
        }
    })