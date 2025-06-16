package com.nyth.app

import android.app.Application
import coil3.ImageLoader
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {
    @Inject
    lateinit var imageLoader: ImageLoader
}