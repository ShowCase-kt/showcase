plugins {
    kotlin("multiplatform")
}

kotlin {
    sourceSets {
        jvm()

        val jvmMain by getting {
            dependencies {
                api(project(":Core"))
                implementation("org.apache.poi:poi:5.2.4")
                implementation("org.apache.poi:poi-ooxml:5.2.4")
                implementation("org.apache.poi:poi-ooxml-full:5.2.4")
                implementation("com.soywiz.korlibs.krypto:krypto:4.0.10")
            }
        }
    }

    publishing {
        publications {
            withType(MavenPublication::class) {
                val id = "ShowCase-Exports.pptx"

                artifactId = if (name != "kotlinMultiplatform") "$id-$name"
                    else id
            }
        }
    }
}