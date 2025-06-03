package com.nyth.app.core.model.local

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class BottomNavigationItem(
    @StringRes val labelRes: Int,
    @DrawableRes val iconRes: Int,
    val route: Screen
)