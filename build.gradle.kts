plugins {
    kotlin("multiplatform") version libs.versions.kotlin.get() apply false
    kotlin("plugin.serialization") version libs.versions.kotlin.get() apply false
    id("com.android.library") version libs.versions.android.gradle.plugin.get() apply false
    id("co.touchlab.kmmbridge") version libs.versions.kmmBridge.get() apply false
    id("app.cash.sqldelight") version libs.versions.sqlDelight.get() apply false
    id("co.touchlab.skie") version libs.versions.skie.get() apply false
    id("dev.mokkery") version libs.versions.mokkery.get() apply false
        id("com.autonomousapps.dependency-analysis") version "1.30.0"
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

val autoVersion = project.property(
    if (project.hasProperty("AUTO_VERSION")) {
        "AUTO_VERSION"
    } else {
        "LIBRARY_VERSION"
    }
) as String

subprojects {
    val GROUP: String by project
    group = GROUP
    version = autoVersion
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
