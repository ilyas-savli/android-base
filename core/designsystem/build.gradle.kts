import ModuleDependency.Project.coreModel
import ModuleDependency.Project.coreNetwork

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.stack.android.library)
    alias(libs.plugins.stack.kotlin.android)
    alias(libs.plugins.stack.kotlin.kapt)
    alias(libs.plugins.stack.kotlin.parcelize)
}

android {
    namespace = AppConfig.namespaceDesignSystem

    compileSdk = AppConfig.compileSdk

    defaultConfig {
        minSdk = AppConfig.minSdk
    }

    lint {
        abortOnError = false
        htmlReport = true
        xmlReport = false
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildTypes {
        debug {
        }
        release {
        }
    }

    kotlinOptions {
        jvmTarget = AppConfig.jvmTarget
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = AppConfig.composeCompiler
    }
}

dependencies {
    api(coreModel())
    api(coreNetwork())

    // ui
    api(libs.androidx.compose.foundation)
    api(libs.androidx.flowrow)
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.material.core)
    api(libs.androidx.compose.material3)
    api(libs.androidx.material3.window)
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.ui.util)
    api(libs.androidx.core.core)

    // balloon
    api(libs.stack.ui.balloon)

    debugApi(libs.androidx.compose.ui.tooling)

    api(libs.coil.kt.compose)
    api(libs.coil.kt.video)
    api(libs.coil.kt.svg)

    api(libs.stack.kotlin.reflect)
    // system bars customization
    api(libs.stack.accompanist.systemui)

    // hilt compose
    api(libs.androidx.hilt.navigation.compose)

    // lifecycle compose
    api(libs.androidx.lifecycle.compose)

    // navigation
    api(libs.androidx.navigation.compose)
    api(libs.androidx.navigation.dynamic) {
        exclude(group = "com.google.android.play", module = "feature-delivery")
    }

    //exoplayer
    implementation(libs.stack.android.exoplayer)

    //youtubeplayer
    implementation (libs.stack.youtube.player)
    implementation (libs.stack.youtube.player.custom.ui)
}