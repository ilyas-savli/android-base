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
val ciCdProperties = Properties().apply {
    rootProject.file("cicd.properties").inputStream().use { load(it) }
}

android {
    namespace = AppConfig.NAMESPACE_APP

    signingConfigs {
        getByName(AppConfig.DEBUG) {
            storeFile = file(AppConfig.DEMO_JKS_FILE_PATH)
            storePassword = AppConfig.STORE_PASSWORD
            keyAlias = AppConfig.KEY_ALIAS
            keyPassword = AppConfig.KEY_PASSWORD
        }
        create(AppConfig.STAGE) {
            storeFile = file(AppConfig.DEMO_JKS_FILE_PATH)
            storePassword = AppConfig.STORE_PASSWORD
            keyAlias = AppConfig.KEY_ALIAS
            keyPassword = AppConfig.KEY_PASSWORD
        }
        create(AppConfig.PROD) {
            storeFile = file(AppConfig.DEMO_JKS_FILE_PATH)
            storePassword = AppConfig.STORE_PASSWORD
            keyAlias = AppConfig.KEY_ALIAS
            keyPassword = AppConfig.KEY_PASSWORD
        }
    }

    defaultConfig {
        applicationId = AppConfig.APPLICATION_ID
        minSdk = AppConfig.MIN_SDK
        compileSdk = AppConfig.COMPILE_SDK
        targetSdk = AppConfig.TARGET_SDK

        versionCode = AppConfig.VERSION_CODE

        versionName = ciCdProperties.getProperty("VERSION_NAME") ?: AppConfig.VERSION_NAME

        testInstrumentationRunner = AppConfig.TEST_INSTRUMENTATION_RUNNER

        vectorDrawables {
            useSupportLibrary = true
        }

        lint {
            abortOnError = false
        }

        flavorDimensions += AppConfig.FLAVOR_DIMENSION
    }

    buildTypes {
        debug {
            applicationIdSuffix = AppConfig.DEBUG_APP_ID_SUFFIX
            versionNameSuffix = AppConfig.DEBUG_VERSION_NAME_SUFFIX
            manifestPlaceholders["deepLinkHost"] = AppConfig.DEEP_LINK_HOST_STAGE
        }

        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName(AppConfig.PROD)
            manifestPlaceholders["deepLinkHost"] = AppConfig.DEEP_LINK_HOST_PROD
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = AppConfig.JVM_TARGET
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = AppConfig.COMPOSE_COMPILER
    }

    productFlavors {
        create(AppConfig.PROD) {
            dimension = AppConfig.FLAVOR_DIMENSION

            manifestPlaceholders["appLabel"] = AppConfig.PROD_APP_NAME
            manifestPlaceholders["applicationIcon"] = AppConfig.PROD_ICON
            manifestPlaceholders["applicationRoundIcon"] = AppConfig.PROD_ROUND_ICON

            versionName = AppConfig.VERSION_NAME
            versionName = ciCdProperties.getProperty("VERSION_NAME") ?: AppConfig.STAGE_VERSION_NAME
        }
        create(AppConfig.STAGE) {
            dimension = AppConfig.FLAVOR_DIMENSION

            applicationIdSuffix = AppConfig.STAGE_APP_ID_SUFFIX
            versionNameSuffix = AppConfig.STAGE_VERSION_NAME_SUFFIX

            manifestPlaceholders["appLabel"] = AppConfig.DEV_APP_NAME
            manifestPlaceholders["applicationIcon"] = AppConfig.STAGE_ICON
            manifestPlaceholders["applicationRoundIcon"] = AppConfig.STAGE_ROUND_ICON

            versionName = ciCdProperties.getProperty("VERSION_NAME") ?: AppConfig.VERSION_NAME
        }
    }

    /**
     * Can distribute the Release app from terminal via typing
     * For PROD Version -> gradle assembleRelease appDistributionUploadProdRelease
     * For DEV Version -> gradle assembleRelease appDistributionUploadDevRelease
     *
     * Can distribute the Debug app from terminal via typing
     * For PROD Version -> gradle assembleDebug appDistributionUploadProdDebug
     * For DEV Version -> gradle assembleDebug appDistributionUploadDevDebug
     */
    // Access properties
    val targetFlavor: String = ciCdProperties.getProperty("TARGET_FLAVOR") ?: AppConfig.STAGE
    val targetBuildType: String =
        ciCdProperties.getProperty("TARGET_BUILD_TYPE") ?: AppConfig.DEBUG

    androidComponents.beforeVariants { variant ->
        variant.enable =
            targetFlavor == variant.flavorName && targetBuildType == variant.buildType

        var releaseNote = ""
        if (variant.enable) {
            releaseNote = when (variant.flavorName) {
                AppConfig.STAGE -> {
                    AppConfig.stageNotes
                }

                else -> {
                    AppConfig.prodNotes
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
