plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("app.cash.sqldelight")
    id("com.android.library")
    `maven-publish`
}

kotlin {
    @Suppress("OPT_IN_USAGE")
    targetHierarchy.default()
    androidTarget {
        publishAllLibraryVariants()
    }
    ios()
    // Note: iosSimulatorArm64 target requires that all dependencies have M1 support
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":analytics"))
                implementation(libs.coroutines.core)
                implementation(libs.bundles.ktor.common)
                implementation(libs.multiplatformSettings)
                implementation(libs.kotlinx.dateTime)
                implementation(libs.touchlab.kermit)
                implementation(libs.sqlDelight.coroutinesExt)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.sqlDelight.android)
                implementation(libs.ktor.client.okHttp)
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(libs.touchlab.stately.common)
                implementation(libs.sqlDelight.native)
                implementation(libs.ktor.client.ios)
            }
        }
    }
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    defaultConfig {
        @Suppress("UnstableApiUsage")
        minSdk = libs.versions.minSdk.get().toInt()
    }
    namespace = "com.listshop.bffpoc.listshop"

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/versions/9/*prev*bin"
        }
    }
}

addGithubPackagesRepository()

sqldelight {
    databases.create("ListshopDb") {
        packageName.set("com.listshop.bff.db")
    }
}
