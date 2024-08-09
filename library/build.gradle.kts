plugins {
    alias(libs.plugins.kotlinMultiplatform)
    id("maven-publish")
}

group = "com.github.gizcerbes"
version = "0.2.a"


kotlin {

        jvm()
        iosX64()
        iosArm64()
        iosSimulatorArm64()

}