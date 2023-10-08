plugins {
    kotlin("multiplatform")
}

repositories {
    mavenCentral()
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
                api("com.soywiz.korlibs.korio:korio:4.0.9")
                api("com.soywiz.korlibs.korim:korim:4.0.9")
                implementation("com.github.ajalt.colormath:colormath:3.3.2")
            }
        }
        /*val jvmMain by getting {
            dependencies {
                implementation("org.apache.poi:poi:5.2.4")
                implementation("org.apache.poi:poi-ooxml:5.2.4")
                implementation("org.apache.poi:poi-ooxml-full:5.2.4")
                implementation("com.soywiz.korlibs.krypto:krypto:4.0.10")
            }
        }*/
        /*val jsMain by getting
        val nativeMain by getting*/
    }

    publishing {
        publications {
            withType(MavenPublication::class) {
                val id = "ShowCase-Core"

                artifactId = if (name != "kotlinMultiplatform") "$id-$name"
                else id
            }
        }
    }
}
