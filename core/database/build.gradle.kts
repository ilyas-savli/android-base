import ModuleDependency.Project.coreModel

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.stack.android.library)
    alias(libs.plugins.stack.kotlin.android)
    alias(libs.plugins.stack.kotlin.kapt)
    alias(libs.plugins.stack.kotlin.parcelize)
    alias(libs.plugins.stack.hilt.plugin)
    alias(libs.plugins.stack.ksp)
}

android {
    namespace = AppConfig.namespaceDatabase

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

    implementation(libs.stack.tink)
    implementation(libs.androidx.data.store)

    implementation(libs.androidx.security.crypto)

    // room
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // hilt
    implementation(libs.stack.hilt.android)
    kapt(libs.stack.hilt.compiler)
}