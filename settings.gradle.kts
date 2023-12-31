include(":Core")

include(":Exporters")

include(":Exporters:pptx")

include(":Exporters:pptx-experimental")

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