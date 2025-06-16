plugins {
    alias(libs.plugins.stack.android.application) apply false
    alias(libs.plugins.stack.android.library) apply false
    alias(libs.plugins.stack.kotlin.android) apply false
    alias(libs.plugins.stack.kotlin.kapt) apply false
    alias(libs.plugins.stack.hilt.plugin) apply false
    alias(libs.plugins.stack.ksp) apply false
    alias(libs.plugins.stack.kotlin.serialization) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.stack.googleService) apply false
    alias(libs.plugins.stack.crashlytics) apply false
    alias(libs.plugins.stack.firebase.perf) apply false
    alias(libs.plugins.stack.firebase.app.distribution) apply false
}
