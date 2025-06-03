import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * Features Management Class
 * Reaching all feature dependencies from this class
 */
object ModuleDependency {
    private const val path = "path"

    /**
     * Core Module Paths
     */
    private const val app = ":app"
    private const val coreModel = ":core:model"
    private const val coreNetwork = ":core:network"
    private const val coreDatabase = ":core:database"
    private const val coreDesignSystem = ":core:designsystem"

    /**
     * Feature Module Paths
     */
    private const val featureHome = ":feature:home"
    private const val featureAuth = ":feature:auth"

    object Project {
        /**
         * Core Modules
         */
        fun DependencyHandler.app(): Dependency = project(mapOf(path to app))
        fun DependencyHandler.coreModel(): Dependency = project(mapOf(path to coreModel))
        fun DependencyHandler.coreNetwork(): Dependency = project(mapOf(path to coreNetwork))
        fun DependencyHandler.coreDatabase(): Dependency = project(mapOf(path to coreDatabase))
        fun DependencyHandler.coreDesignSystem(): Dependency =
            project(mapOf(path to coreDesignSystem))

        /**
         * Feature Modules
         */
        fun DependencyHandler.featureAuth(): Dependency =
            project(mapOf(path to featureAuth))

        fun DependencyHandler.featureListing(): Dependency =
            project(mapOf(path to featureHome))
    }
}
