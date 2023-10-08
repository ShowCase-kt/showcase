plugins {
    kotlin("multiplatform")
}

kotlin {
    jvm()
    linuxX64()
    linuxArm64()
    macosX64()
    macosArm64()
    mingwX64()
    js()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":Core"))
                implementation("com.github.ajalt.colormath:colormath:3.3.1")
            }
        }
    }

    publishing {
        publications {
            withType(MavenPublication::class) {
                val id = "ShowCase-Themes.nova"

                artifactId = if (name != "kotlinMultiplatform") "$id-$name"
                else id
            }
        }
    }
}