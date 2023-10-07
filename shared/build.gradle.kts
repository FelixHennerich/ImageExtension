plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("maven-publish")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }

}

group = "com.github.felixhennerich"
version = "1.0.11"

publishing {
    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/FelixHennerich/ImageExtension")
            credentials {
                username = "felixhennerich"
                password = "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"
            }
        }
    }
}

android {
    namespace = "de.hennerich.imageextension"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}
