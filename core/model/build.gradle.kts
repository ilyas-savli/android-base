@file:Suppress("UnstableApiUsage")

import com.android.build.api.dsl.LibraryBuildType

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.stack.android.library)
    alias(libs.plugins.stack.kotlin.android)
    alias(libs.plugins.stack.kotlin.parcelize)
    alias(libs.plugins.stack.kotlin.serialization)
    alias(libs.plugins.stack.ksp)
}

android {
    namespace = AppConfig.namespaceModel

    compileSdk = AppConfig.compileSdk

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        minSdk = AppConfig.minSdk
    }

    lint {
        abortOnError = false
        htmlReport = true
        xmlReport = false
    }

    buildTypes {
        debug {
            stringField(Field.WEB_BASE_URL to "https://sm-web.androidbase.com/")
            stringField(Field.PAYMENT_LISTEN_HOST to "sm-web.androidbase.com/")
        }
        release {
            stringField(Field.WEB_BASE_URL to "https://sm-web.androidbase.com/")
            stringField(Field.PAYMENT_LISTEN_HOST to "sm-web.androidbase.com/")
            consumerProguardFiles("consumer-rules.pro")
        }
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
    // room
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.runtime.android)
    ksp(libs.androidx.room.compiler)

    // moshi
    implementation(libs.stack.moshi.kotlin)

    // timber
    api(libs.stack.timber)

    api(libs.stack.kotlinx.serialization.json)
}

fun LibraryBuildType.stringField(entry: Pair<String, String>) {
    buildConfigField("String", entry.first, "\"${entry.second}\"")
}