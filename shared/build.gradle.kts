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
            val artifactid = "image-extension"
            val libversion = "1.0.7"
            register<MavenPublication>("gpr"){
                from(components.findByName("shared"))

                groupId = "com.github.felixhennerich"
                artifactId = artifactid
                version = libversion
            }
        }
    }
}