import com.android.build.api.dsl.LibraryBuildType
import java.util.Properties

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.stack.android.library)
    alias(libs.plugins.stack.kotlin.android)
    alias(libs.plugins.stack.hilt.plugin)
    alias(libs.plugins.stack.kotlin.serialization)
    alias(libs.plugins.stack.ksp)
}

android {
    namespace = AppConfig.NAMESPACE_TESTING

    compileSdk = AppConfig.COMPILE_SDK

    buildFeatures.buildConfig = true

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
}

dependencies {
    api(libs.kotlinx.coroutines.test)
    api(projects.core.model)

    implementation(libs.androidx.test.rules)
    implementation(libs.hilt.android.testing)

    // hilt
    implementation(libs.stack.hilt.android)
    ksp(libs.stack.hilt.compiler)

    // test
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
}

fun LibraryBuildType.stringField(entry: Pair<String, String>) {
    buildConfigField("String", entry.first, "\"${entry.second}\"")
}