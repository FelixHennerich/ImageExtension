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

android {
    namespace = "de.hennerich.imageextension"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}

afterEvaluate{
    publishing {
        publications {
            register<MavenPublication>("gpr"){
                from(components["kotlin"])
                groupId = "com.github.felixhennerich"
                artifactId = "image-extension"
                version = "1.0.1"
            }
        }
    }
}