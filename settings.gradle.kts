@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "androidbase"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
// app
include(":app")

// core modules
include(":core:model")
include(":core:network")
include(":core:database")
include(":core:designsystem")
include(":core:testing")

// feature modules
include(":feature:home")
include(":feature:auth")

check(JavaVersion.current().isCompatibleWith(JavaVersion.VERSION_17)) {
    """
    This App requires JDK 17+ but it is currently using JDK ${JavaVersion.current()}.
    Java Home: [${System.getProperty("java.home")}]
    https://developer.android.com/build/jdks#jdk-config-in-studio
    """.trimIndent()
}
