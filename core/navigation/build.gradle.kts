@file:Suppress("UnstableApiUsage")

import ModuleDependency.Project.coreModel

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.stack.android.library)
    alias(libs.plugins.stack.kotlin.android)
    alias(libs.plugins.stack.kotlin.parcelize)
    alias(libs.plugins.stack.ksp)
}

android {
    namespace = AppConfig.namespaceNavigation

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
}

dependencies {
    implementation(coreModel())

    // navigation
    api(libs.androidx.navigation.compose)
    api(libs.androidx.navigation.dynamic) {
        exclude(group = "com.google.android.play", module = "feature-delivery")
    }
}