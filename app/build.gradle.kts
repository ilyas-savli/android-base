import com.android.build.api.dsl.ApplicationProductFlavor
import com.google.firebase.appdistribution.gradle.firebaseAppDistribution
import java.util.Properties

plugins {
    alias(libs.plugins.stack.android.application)
    alias(libs.plugins.stack.kotlin.kapt)
    alias(libs.plugins.stack.hilt.plugin)
    alias(libs.plugins.stack.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.stack.googleService)
    alias(libs.plugins.stack.crashlytics)
    alias(libs.plugins.stack.firebase.app.distribution)
    alias(libs.plugins.stack.ksp)
}

// Load local.properties
val localProperties = Properties().apply {
    rootProject.file("local.properties").inputStream().use { load(it) }
}

android {
    namespace = AppConfig.namespaceApp

    signingConfigs {
        getByName("debug") {
            storeFile = file(AppConfig.demoJksFilePath)
            storePassword = AppConfig.storePassword
            keyAlias = AppConfig.keyAlias
            keyPassword = AppConfig.keyPassword
        }
        create(AppConfig.stage) {
            storeFile = file(AppConfig.demoJksFilePath)
            storePassword = AppConfig.storePassword
            keyAlias = AppConfig.keyAlias
            keyPassword = AppConfig.keyPassword
        }
        create(AppConfig.prod) {
            storeFile = file(AppConfig.demoJksFilePath)
            storePassword = AppConfig.storePassword
            keyAlias = AppConfig.keyAlias
            keyPassword = AppConfig.keyPassword
        }
    }

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdk
        compileSdk = AppConfig.compileSdk
        targetSdk = AppConfig.targetSdk

        versionCode = AppConfig.versionCode

        versionName = localProperties.getProperty("VERSION_NAME") ?: AppConfig.versionName

        testInstrumentationRunner = AppConfig.testInstrumentationRunner

        vectorDrawables {
            useSupportLibrary = true
        }

        lint {
            abortOnError = false
        }

        flavorDimensions += AppConfig.flavorDimension
    }

    buildTypes {
        debug {
            applicationIdSuffix = AppConfig.debugAppIdSuffix
            versionNameSuffix = AppConfig.debugVersionNameSuffix
            manifestPlaceholders["deepLinkHost"] = AppConfig.deepLinkHostStage
        }

        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName(AppConfig.prod)
            manifestPlaceholders["deepLinkHost"] = AppConfig.deepLinkHostProd
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = AppConfig.jvmTarget
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = AppConfig.composeCompiler
    }

    productFlavors {
        create(AppConfig.prod) {
            dimension = AppConfig.flavorDimension

            manifestPlaceholders["appLabel"] = AppConfig.prodAppName
            manifestPlaceholders["applicationIcon"] = AppConfig.prodIcon
            manifestPlaceholders["applicationRoundIcon"] = AppConfig.prodRoundIcon

            versionName = localProperties.getProperty("VERSION_NAME") ?: AppConfig.versionName
        }
        create(AppConfig.stage) {
            dimension = AppConfig.flavorDimension

            applicationIdSuffix = AppConfig.stageAppIdSuffix
            versionNameSuffix = AppConfig.stageVersionNameSuffix

            manifestPlaceholders["appLabel"] = AppConfig.devAppName
            manifestPlaceholders["applicationIcon"] = AppConfig.stageIcon
            manifestPlaceholders["applicationRoundIcon"] = AppConfig.stageRoundIcon

            versionName = localProperties.getProperty("VERSION_NAME") ?: AppConfig.stageVersionName
        }
    }

    // Access properties
    val targetFlavor: String = localProperties.getProperty("TARGET_FLAVOR") ?: AppConfig.stage
    val targetBuildType: String =
        localProperties.getProperty("TARGET_BUILD_TYPE") ?: AppConfig.debug

    androidComponents.beforeVariants { variant ->
        variant.enable =
            targetFlavor == variant.flavorName && targetBuildType == variant.buildType

        var releaseNote = ""
        if (variant.enable) {
            releaseNote = when (variant.flavorName) {
                AppConfig.stage -> {
                    "READY FOR STAGE kartları test edilebilir."
                }

                else -> {
                    "READY FOR PRODUCTION kartları test edilebilir."
                }
            }
        }

        println(releaseNote + " " + variant.flavorName + " " + variant.buildType + " " + variant.enable)

        // Add Specific Notes
        firebaseAppDistribution {
            releaseNotes = releaseNote
        }
    }
}

dependencies {
    // core modules
    implementation(projects.core.database)
    implementation(projects.core.designsystem)
    implementation(projects.core.network)

    // feature modules
    implementation(projects.feature.auth)
    implementation(projects.feature.home)

    // hilt
    implementation(libs.stack.hilt.android)
    kapt(libs.stack.hilt.compiler)

    implementation(libs.androidx.startup.runtime)

    // Import the BoM for the Firebase platform
    implementation(platform(libs.stack.firebase.bom))

    // When using the BoM, you don"t specify versions in Firebase library dependencies
    implementation(libs.stack.firebase.crashlytics)
    implementation(libs.stack.firebase.analytics)
    implementation(libs.stack.firebase.messaging)

    implementation(libs.auth.googleService)

    implementation(libs.stack.google.location)
    implementation(libs.stack.firebase.iid)

    implementation(libs.androidx.splash.screen)

    debugImplementation(libs.stack.leakcanary)
}

fun ApplicationProductFlavor.stringField(entry: Pair<String, String>) {
    buildConfigField("String", entry.first, "\"${entry.second}\"")
}
