plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("app.cash.sqldelight")
    id("com.android.library")
    id("dev.mokkery")
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
                api(project(":analytics"))
                api(libs.kotlin.stdlib)
                api(libs.kotlinx.serialization.core)
                implementation(libs.kotlinx.serialization.core)
                implementation(libs.coroutines.core)
                implementation(libs.bundles.ktor.common)
                implementation(libs.multiplatformSettings)
                implementation(libs.kotlinx.dateTime)
                implementation(libs.touchlab.kermit)
                implementation(libs.sqlDelight.coroutinesExt)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                //implementation(libs.resources.test)
                implementation(libs.coroutines.test)
                //implementation(libs.turbine)
                implementation(libs.kotest.framework.engine)
                implementation(libs.kotest.assertions.core)
                implementation("com.goncalossilva:resources:0.4.0")
                //implementation(libs.ktor.client.mock)
            }
        }

        val androidTest = sourceSets.getByName("androidUnitTest") {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation(libs.junit)
                implementation(libs.sqldelight.jdbc.driver)
                implementation(libs.sqldelight.sqlite.driver)
                implementation(libs.mock.server)
                //implementation(libs.sqldelight.jvm)
                //implementation(libs.coroutines.test)
                //implementation(libs.turbine)
                //implementation(libs.kotest.framework.engine)
                //implementation(libs.ktor.client.mock)
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

    task("testClasses")
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
