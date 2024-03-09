package com.listshop.bffpoc

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import co.touchlab.kmmbridgekickstart.AnalyticsHandle
import com.listshop.bffpoc.db.ListshopPOCDb
import com.listshop.bffpoc.repository.TagUCP
import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import platform.Foundation.NSUserDefaults

fun tagUCPStartup(analyticsHandle: AnalyticsHandle): TagUCP {
    val locator = IOSServiceLocator(
        NSUserDefaults(suiteName = SETTINGS_KEY),
        analyticsHandle = analyticsHandle
    )
    return locator.tagUCP
}

internal class IOSServiceLocator(
    userDefaults: NSUserDefaults,
    analyticsHandle: AnalyticsHandle
) : BaseServiceLocator(analyticsHandle) {

    override val sqlDriver: SqlDriver by lazy {
        NativeSqliteDriver(ListshopPOCDb.Schema, DB_NAME)
    }

    override val settings: Settings by lazy { NSUserDefaultsSettings(userDefaults) }

    override val clientEngine: HttpClientEngine by lazy { Darwin.create() }
}
