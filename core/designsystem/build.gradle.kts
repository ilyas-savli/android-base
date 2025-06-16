@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.stack.android.library)
    alias(libs.plugins.stack.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.stack.kotlin.serialization)
    alias(libs.plugins.stack.ksp)
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
    api(projects.core.model)
    api(projects.core.network)

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
    api(libs.androidx.core.ktx)

    // navigation3
    api(libs.androidx.navigation3.runtime)
    api(libs.androidx.navigation3.ui)
    api(libs.androidx.navigation3.lifecycle)

    debugApi(libs.androidx.compose.ui.tooling)

    api(libs.stack.kotlin.reflect)
    // system bars customization
    api(libs.stack.accompanist.systemui)

    // hilt compose
    api(libs.androidx.hilt.navigation.compose)

    // lifecycle compose
    api(libs.androidx.lifecycle.compose)

    api(libs.bundles.coil)

    // navigation
    api(libs.androidx.navigation.compose)
    api(libs.androidx.navigation.dynamic) {
        exclude(group = "com.google.android.play", module = "feature-delivery")
    }
}