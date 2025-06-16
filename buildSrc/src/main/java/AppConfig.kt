object AppConfig {
    const val applicationId = "com.nyth.app"

    // namespace app
    const val namespaceApp = "com.nyth.app"

    // namespace core modules
    const val namespaceModel = "com.nyth.app.core.model"
    const val namespaceNetwork = "com.nyth.app.core.network"
    const val namespaceDatabase = "com.nyth.app.core.database"
    const val namespaceNavigation = "com.nyth.app.core.navigation"
    const val namespaceDesignSystem = "com.nyth.app.core.designsystem"

    const val namespaceTesting = "com.nyth.app.core.testing"

    // namespace feature modules
    const val namespaceAuth = "com.nyth.app.feature.auth"
    const val namespaceListing = "com.nyth.app.feature.listing"

    // version info
    private const val versionMajor = 1
    private const val versionMinor = 0
    private const val versionPatch = 0

    const val versionName = "v$versionMajor.$versionMinor.$versionPatch"
    const val versionCode = 1

    // sdk configs
    const val compileSdk = 35
    const val minSdk = 26
    const val targetSdk = 35

    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    // flavors
    const val stage = "stage"
    const val prod = "prod"

    // build types
    const val debug = "debug"
    const val release = "release"

    // signing configs
    const val demoJksFilePath = "../config/demoJks"
    const val storePassword = "demo1234"
    const val keyAlias = "key0"
    const val keyPassword = "demo1234"

    // flavor configs
    const val flavorDimension = "version"
    const val debugAppIdSuffix = ".debug"
    const val debugVersionNameSuffix = "-debug"
    const val devAppName = "androidbase stage"
    const val prodAppName = "androidbase"

    // stage icons
    const val stageIcon = "@mipmap/ic_launcher_stage"
    const val stageRoundIcon = "@mipmap/ic_launcher_stage_round"
    const val prodIcon = "@mipmap/ic_launcher"
    const val prodRoundIcon = "@mipmap/ic_launcher_round"

    // stage configs
    const val stageAppIdSuffix = ".stage"
    const val stageVersionNameSuffix = "-stage"
    const val stageVersionName = "v1.0.91"

    // jvm configs
    const val jvmTarget = "17"

    // compose compiler version
    const val composeCompiler = "1.4.2"

    const val deepLinkHostStage = "sm-web.androidbase.com"

    //DeepLink
    const val deepLinkHostProd = "www.androidbase.com"
}