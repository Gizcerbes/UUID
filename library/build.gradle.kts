plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

group = "com.github.Gizcerbes"

kotlin {
    jvm()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

}