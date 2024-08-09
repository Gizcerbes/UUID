plugins {
    alias(libs.plugins.kotlinMultiplatform)
    id("maven-publish")
}

group = "com.github.Gizcerbes"

kotlin {
    jvm()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

}