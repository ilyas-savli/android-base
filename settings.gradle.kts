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
// app
include(":app")

// core modules
include(":core:model")
include(":core:network")
include(":core:database")
include(":core:designsystem")
include(":core:navigation")

// feature modules
include(":feature:home")
include(":feature:auth")
