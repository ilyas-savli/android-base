@file:Suppress("UnstableApiUsage")

import com.android.build.api.dsl.LibraryBuildType

@Suppress("DSL_SCOPE_VIOLATION") plugins {
    alias(libs.plugins.stack.android.library)
    alias(libs.plugins.stack.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.stack.kotlin.serialization)
    alias(libs.plugins.stack.ksp)
}

android {
    namespace = AppConfig.NAMESPACE_MODEL

    compileSdk = AppConfig.COMPILE_SDK

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        minSdk = AppConfig.MIN_SDK
    }

    lint {
        abortOnError = false
        htmlReport = true
        xmlReport = false
    }

    buildTypes {
        debug {}
        release {
            consumerProguardFiles("consumer-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = AppConfig.JVM_TARGET
    }
}

dependencies {
    // room
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)

    implementation(libs.androidx.runtime.android)

    // moshi
    implementation(libs.stack.moshi.kotlin)

    // timber
    api(libs.stack.timber)

    api(libs.stack.kotlinx.serialization.json)
}

fun LibraryBuildType.stringField(entry: Pair<String, String>) {
    buildConfigField("String", entry.first, "\"${entry.second}\"")
}