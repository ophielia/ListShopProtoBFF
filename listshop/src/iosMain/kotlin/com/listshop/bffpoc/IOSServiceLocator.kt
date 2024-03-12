package com.listshop.bffpoc

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import co.touchlab.kmmbridgekickstart.AnalyticsHandle
import co.touchlab.kmmbridgekickstart.AppInfo
import com.listshop.bff.BaseServiceLocator
import com.listshop.bff.DB_NAME
import com.listshop.bff.SETTINGS_KEY
import com.listshop.bff.db.ListshopDb
import com.listshop.bff.ucp.OnboardingUCP
import com.listshop.bff.ucp.TagUCP
import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import platform.Foundation.NSUserDefaults

fun tagUCPStartup(analyticsHandle: AnalyticsHandle, appInfo: AppInfo): TagUCP {
    val locator = IOSServiceLocator(
        NSUserDefaults(suiteName = SETTINGS_KEY),
        analyticsHandle = analyticsHandle,
        appInfo = appInfo
    )
    return locator.tagUCP
}

fun onboardingUCPStartup(analyticsHandle: AnalyticsHandle, appInfo: AppInfo): OnboardingUCP {
    val locator = IOSServiceLocator(
        NSUserDefaults(suiteName = SETTINGS_KEY),
        analyticsHandle = analyticsHandle,
        appInfo = appInfo
    )
    return locator.onboardingUCP
}

internal class IOSServiceLocator(
    userDefaults: NSUserDefaults,
    analyticsHandle: AnalyticsHandle,
    appInfo: AppInfo
) : BaseServiceLocator(analyticsHandle, appInfo) {

    override val sqlDriver: SqlDriver by lazy {
        NativeSqliteDriver(ListshopDb.Schema, DB_NAME)
    }

    override val settings: Settings by lazy { NSUserDefaultsSettings(userDefaults) }

    override val clientEngine: HttpClientEngine by lazy { Darwin.create() }
}
