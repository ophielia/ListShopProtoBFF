plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.listshop.bffpoc.test.android"
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        applicationId = "com.listshop.bffpoc.test.android"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }

    lint {
        warningsAsErrors = false
        abortOnError = true
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/versions/9/previous-compilation-data.bin"
        }
    }
}

val remoteBuild = false

if (remoteBuild) {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/[your org]/[your repo]")
            credentials {
                username = project.property("GITHUB_PACKAGES_USERNAME") as String
                password = project.property("GITHUB_PACKAGES_PASSWORD") as String
            }
        }
    }
}

val GROUP:String by project
val LIBRARY_VERSION:String by project

dependencies {
    if (remoteBuild) {
        implementation("${GROUP}:analytics-android-debug:${LIBRARY_VERSION}.+")
        implementation("${GROUP}:listshop-android-debug:${LIBRARY_VERSION}.+")
    } else {
        implementation(project(":analytics"))
        implementation(project(":listshop"))
    }
    implementation(libs.bundles.app.ui)
    implementation(libs.koin.android)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.swiperefreshlayout)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.cardview)

    implementation(libs.androidx.core)
    implementation("androidx.cardview:cardview:1.0.0")


    implementation(libs.androidx.constraintlayout)
    coreLibraryDesugaring(libs.android.desugaring)
}

/*
dependencies {

    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    implementation("androidx.cardview:cardview:1.0.0")
}
 */