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
            }
        }

        val jvmMain by getting {
            dependencies {
                api(project("pptx"))
            }
        }
    }

    publishing {
        publications {
            withType(MavenPublication::class) {
                val id = "ShowCase-Exporters"

                artifactId = if (name != "kotlinMultiplatform") "$id-$name"
                else id
            }
        }
    }
}