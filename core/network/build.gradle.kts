import com.android.build.api.dsl.LibraryBuildType
import java.util.Properties

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.stack.android.library)
    alias(libs.plugins.stack.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.stack.kotlin.kapt)
    alias(libs.plugins.stack.hilt.plugin)
    alias(libs.plugins.stack.ksp)
}

val localProperties = Properties().apply {
    rootProject.file("local.properties").inputStream().use { load(it) }
}

android {
    namespace = AppConfig.namespaceNetwork

    compileSdk = AppConfig.compileSdk

    buildFeatures.buildConfig = true

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
            stringField(Field.IDENTITY_BASE_URL to "https://stage-androidbase.com/")
            stringField(Field.LISTING_BASE_URL to "https://stage-androidbase.com/")
            stringField(Field.CONTENT_BASE_URL to "https://stage-androidbase.com/")
            stringField(Field.PRAY_BASE_URL to "https://muslimsalat.com/")
            val debugApiKey = localProperties.getProperty("NETWORK_API_KEY") ?: ""
            buildConfigField("String", "API_KEY", "\"$debugApiKey\"")

        }
        release {
            stringField(Field.IDENTITY_BASE_URL to "https://stage-androidbase.com/")
            stringField(Field.LISTING_BASE_URL to "https://stage-androidbase.com/")
            stringField(Field.CONTENT_BASE_URL to "https://stage-androidbase.com/")
            stringField(Field.PRAY_BASE_URL to "https://muslimsalat.com/")
            val debugApiKey = localProperties.getProperty("NETWORK_API_KEY") ?: ""
            buildConfigField("String", "API_KEY", "\"$debugApiKey\"")
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
    kapt(libs.stack.hilt.compiler)

    // paging
    api(libs.androidx.paging.compose)
    api(libs.androidx.paging.runtime)

    implementation(libs.bundles.coil)
}

fun LibraryBuildType.stringField(entry: Pair<String, String>) {
    buildConfigField("String", entry.first, "\"${entry.second}\"")
}