package com.nyth.app.core.model.local

import kotlinx.serialization.Serializable

sealed class Screen {
    /**
     * auth
     */
    @Serializable
    object AuthSplash : Screen()

    /**
     * BottomBar
     */
    @Serializable
    object DashboardScreen : Screen()

    @Serializable
    object Qibla : Screen()

    @Serializable
    object Settings : Screen()

    @Serializable
    object PrivacyPolicy : Screen()
}