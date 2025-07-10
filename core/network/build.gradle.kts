import com.android.build.api.dsl.LibraryBuildType

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.stack.android.library)
    alias(libs.plugins.stack.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.stack.hilt.plugin)
    alias(libs.plugins.stack.ksp)
}

android {
    namespace = AppConfig.NAMESPACE_NETWORK

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

    buildTypes {
        debug {
            stringField(Field.PRAY_BASE_URL to "https://muslimsalat.com/")
        }
        release {
            stringField(Field.PRAY_BASE_URL to "https://muslimsalat.com/")
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
    implementation(projects.core.model)
    implementation(projects.core.database)

    implementation(libs.stack.okhttp.interceptor)

    // coroutines
    implementation(libs.stack.coroutines)

    // moshi
    implementation(libs.stack.converter.moshi)
    implementation(libs.stack.moshi.kotlin)

    // jwt
    api(libs.stack.jwt)

    // network
    implementation(libs.stack.retrofit.core)

    // chucker
    debugImplementation(libs.stack.chucker)
    releaseImplementation(libs.stack.chucker.noop)

    // hilt
    implementation(libs.stack.hilt.android)
    ksp(libs.stack.hilt.compiler)

    // paging
    api(libs.androidx.paging.compose)
    api(libs.androidx.paging.runtime)

    implementation(libs.bundles.coil)
}

fun LibraryBuildType.stringField(entry: Pair<String, String>) {
    buildConfigField("String", entry.first, "\"${entry.second}\"")
}