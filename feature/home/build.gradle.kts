import ModuleDependency.Project.coreDatabase
import ModuleDependency.Project.coreDesignSystem
import com.android.build.api.dsl.LibraryBuildType

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.stack.android.library)
    alias(libs.plugins.stack.kotlin.android)
    alias(libs.plugins.stack.kotlin.parcelize)
    alias(libs.plugins.stack.kotlin.kapt)
    alias(libs.plugins.stack.hilt.plugin)
    alias(libs.plugins.stack.ksp)
}

android {
    namespace = AppConfig.namespaceListing

    compileSdk = AppConfig.compileSdk

    buildFeatures {
        compose = true
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = AppConfig.jvmTarget
    }

    composeOptions {
        kotlinCompilerExtensionVersion = AppConfig.composeCompiler
    }
}

dependencies {
    implementation(coreDesignSystem())
    implementation(coreDatabase())

    // camerax
    api(libs.androidx.camera.core)
    api(libs.androidx.camera.camera2)
    api(libs.androidx.camera.lifecycle)
    api(libs.androidx.camera.video)
    api(libs.androidx.camera.view)
    api(libs.androidx.camera.extensions)

    //animation
    implementation(libs.androidx.compose.animation)

    //exoplayer
    implementation(libs.stack.android.exoplayer)

    // hilt
    implementation(libs.stack.hilt.android)
    kapt(libs.stack.hilt.compiler)

    implementation(libs.auth.googleService)

    //Jsoup
    implementation(libs.jsoup)
}

fun LibraryBuildType.stringField(entry: Pair<String, String>) {
    buildConfigField("String", entry.first, "\"${entry.second}\"")
}