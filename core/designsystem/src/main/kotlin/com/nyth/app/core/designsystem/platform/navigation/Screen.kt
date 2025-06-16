package com.nyth.app.core.designsystem.platform.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Screen : NavKey {
    @Serializable
    data object Splash : Screen()

    @Serializable
    data object NestedGraph : Screen()
}