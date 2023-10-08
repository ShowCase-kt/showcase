plugins {
    `java-gradle-plugin`
    kotlin("jvm")
    id("com.gradle.plugin-publish") version "1.1.0"
}

gradlePlugin {
    plugins {
        create("Gradle") {
            id = "io.github.rebokdev.ShowCase-Gradle"
            implementationClass = "PluginImplementation"
        }
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(17)
}