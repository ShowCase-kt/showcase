include(":Core")

include(":Exports")

include(":Exports:pptx")

include(":Gradle")

include(":Themes")

include(":Themes:nova")

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }

    plugins {
        kotlin("multiplatform") version extra["kotlin.version"].toString()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

rootProject.name = "ShowCase"