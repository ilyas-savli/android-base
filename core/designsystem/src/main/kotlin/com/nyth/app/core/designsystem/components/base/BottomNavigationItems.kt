package com.nyth.app.core.designsystem.components.base

import com.nyth.app.core.designsystem.R
import com.nyth.app.core.model.local.BottomNavigationItem
import com.nyth.app.core.model.local.Screen

enum class BottomNavigationItems(
    var navigationItems: List<BottomNavigationItem>
) {
    LISTING(
        navigationItems = listOf(
            BottomNavigationItem(
                labelRes = R.string.home_page,
                iconRes = R.drawable.ic_home,
                route = Screen.DashboardScreen
            ),
            BottomNavigationItem(
                labelRes = R.string.qibla,
                iconRes = R.drawable.ic_qibla,
                route = Screen.Qibla
            ),
            BottomNavigationItem(
                labelRes = R.string.settings,
                iconRes = R.drawable.ic_settings,
                route = Screen.Settings
            )
        )
    ),
}