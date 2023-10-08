plugins {
    kotlin("multiplatform")
}

allprojects {
    group = extra["project.group"].toString()
    version = extra["project.version"].toString()

    if (project.name != "Gradle") {
        apply(
            plugin = "maven-publish"
        )
    }
}

kotlin {
    jvm()
    linuxX64()
    macosX64()
    macosArm64()
    mingwX64()
    js()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project("Core"))
                api(project("Themes"))
                api(project("Exports"))
            }
        }
    }
}

task("publish-all") {
    val taskName = "publishToMavenLocal"
    dependsOn(taskName)
    dependsOn(":Core:${taskName}")
    dependsOn(":Exports:${taskName}")
    dependsOn(":Exports:pptx:${taskName}")
    dependsOn(":Themes:${taskName}")
    dependsOn(":Themes:nova:${taskName}")
    dependsOn(":Gradle:${taskName}")
}
