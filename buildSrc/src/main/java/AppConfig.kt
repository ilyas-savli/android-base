object AppConfig {
    const val APPLICATION_ID = "com.nyth.app"

    // region namespaces
    const val NAMESPACE_APP = "com.nyth.app"

    // modules
    const val NAMESPACE_MODEL = "com.nyth.app.core.model"
    const val NAMESPACE_NETWORK = "com.nyth.app.core.network"
    const val NAMESPACE_DATABASE = "com.nyth.app.core.database"
    const val NAMESPACE_DESIGN_SYSTEM = "com.nyth.app.core.designsystem"

    const val NAMESPACE_TESTING = "com.nyth.app.core.testing"

    // features
    const val NAMESPACE_AUTH = "com.nyth.app.feature.auth"
    const val NAMESPACE_LISTING = "com.nyth.app.feature.listing"
    // endregion

    // region version info
    private const val VERSION_MAJOR = 1
    private const val VERSION_MINOR = 0
    private const val VERSION_PATCH = 0

    const val VERSION_NAME = "v$VERSION_MAJOR.$VERSION_MINOR.$VERSION_PATCH"
    const val VERSION_CODE = 1
    // endregion

    // region sdk configs
    const val COMPILE_SDK = 36
    const val MIN_SDK = 26
    const val TARGET_SDK = 36

    const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
    // endregion

    // region flavors
    const val STAGE = "stage"
    const val PROD = "prod"
    // endregion

    // region build types
    const val DEBUG = "debug"
    const val RELEASE = "release"
    // endregion

    // region signing configs
    const val DEMO_JKS_FILE_PATH = "../config/demoJks"
    const val STORE_PASSWORD = "demo1234"
    const val KEY_ALIAS = "key0"
    const val KEY_PASSWORD = "demo1234"
    // endregion

    // region flavor configs
    const val FLAVOR_DIMENSION = "version"
    const val DEBUG_APP_ID_SUFFIX = ".debug"
    const val DEBUG_VERSION_NAME_SUFFIX = "-debug"
    const val DEV_APP_NAME = "androidbase stage"
    const val PROD_APP_NAME = "androidbase"
    // endregion

    // region stage configs
    const val STAGE_ICON = "@mipmap/ic_launcher_stage"
    const val STAGE_ROUND_ICON = "@mipmap/ic_launcher_stage_round"

    // stage configs
    const val STAGE_APP_ID_SUFFIX = ".stage"
    const val STAGE_VERSION_NAME_SUFFIX = "-stage"
    const val STAGE_VERSION_NAME = "v1.0.91"
    // endregion

    // region prod icons
    const val PROD_ICON = "@mipmap/ic_launcher"
    const val PROD_ROUND_ICON = "@mipmap/ic_launcher_round"
    // endregion

    const val JVM_TARGET = "17"

    const val COMPOSE_COMPILER = "1.4.2"

    // region deeplink
    const val DEEP_LINK_HOST_STAGE = "sm-web.androidbase.com"
    const val DEEP_LINK_HOST_PROD = "www.androidbase.com"
    // endregion

    // region distribution notes
    const val prodNotes = "'Prod Test' kartları test edilebilir."
    const val stageNotes = "'Stage Test' kartları test edilebilir."
    // endregion
}