plugins {
    alias(libs.plugins.kotlinMultiplatform)
    id("maven-publish")
}

kotlin {
    jvm()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
}