import com.android.build.api.dsl.LibraryBuildType

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.stack.android.library)
    alias(libs.plugins.stack.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.stack.kotlin.kapt)
    alias(libs.plugins.stack.hilt.plugin)
    alias(libs.plugins.stack.ksp)
}

android {
    namespace = AppConfig.NAMESPACE_AUTH

    compileSdk = AppConfig.COMPILE_SDK

    buildFeatures {
        compose = true
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = AppConfig.JVM_TARGET
    }

    composeOptions {
        kotlinCompilerExtensionVersion = AppConfig.COMPOSE_COMPILER
    }
}

dependencies {
    implementation(projects.core.designsystem)
    implementation(projects.core.database)

    // hilt
    implementation(libs.stack.hilt.android)
    kapt(libs.stack.hilt.compiler)

    implementation(libs.auth.googleService)
}

fun LibraryBuildType.stringField(entry: Pair<String, String>) {
    buildConfigField("String", entry.first, "\"${entry.second}\"")
}