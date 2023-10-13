plugins {
    kotlin("multiplatform")
}

kotlin {
    sourceSets {
        jvm()
        linuxX64()
        linuxArm64()
        macosX64()
        macosArm64()
        mingwX64()
        js()

        val commonMain by getting {
            dependencies {
                api(project(":Core"))
            }
        }
    }

    publishing {
        publications {
            withType(MavenPublication::class) {
                val id = "ShowCase-Exporters.pptx-experimental"

                artifactId = if (name != "kotlinMultiplatform") "$id-$name"
                    else id
            }
        }
    }
}