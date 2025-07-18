package com.nyth.app.initializer

import android.content.Context
import androidx.startup.Initializer
import timber.log.Timber

class AppComponentInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        Timber.d("AppComponentInitializer is initialized")
    }

    override fun dependencies(): List<Class<out Initializer<*>>> =
        listOf(
            TimberInitializer::class.java,
            FirebaseInitializer::class.java
        )
}
