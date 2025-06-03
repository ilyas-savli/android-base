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
    namespace = AppConfig.namespaceAuth

    compileSdk = AppConfig.compileSdk

    buildFeatures {
        compose = true
        buildConfig = true
    }

    buildTypes {
        debug {
            stringField(Field.SERVER_CLIENT_ID to "264597081142-jq4fc5i9ml21up9jm7v7ce77bvvk0iuj.apps.googleusercontent.com")
        }
        release {
            stringField(Field.SERVER_CLIENT_ID to "264597081142-jq4fc5i9ml21up9jm7v7ce77bvvk0iuj.apps.googleusercontent.com")
        }
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

    // hilt
    implementation(libs.stack.hilt.android)
    kapt(libs.stack.hilt.compiler)

    implementation(libs.auth.googleService)
}

fun LibraryBuildType.stringField(entry: Pair<String, String>) {
    buildConfigField("String", entry.first, "\"${entry.second}\"")
}