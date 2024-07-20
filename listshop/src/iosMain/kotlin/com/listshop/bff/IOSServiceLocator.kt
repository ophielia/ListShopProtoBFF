package com.listshop.bff

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.listshop.analytics.AnalyticsHandle
import com.listshop.analytics.AppInfo
import com.listshop.bff.db.ListshopDb
import com.listshop.bff.services.UserSessionService
import com.listshop.bff.ucp.DashboardUCP
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

fun getProviders(analyticsHandle: AnalyticsHandle, appInfo: AppInfo): ProviderCollection {
    val locator = IOSServiceLocator(
        NSUserDefaults(suiteName = SETTINGS_KEY),
        analyticsHandle = analyticsHandle,
        appInfo = appInfo
    )
    return ProviderCollection(
        appAnalytics = analyticsHandle.appAnalytics,
        tagUCP = locator.tagUCP,
        sessionService = locator.sessionService,
        onboardingUCP = locator.onboardingUCP,
        dashboardUCP = locator.dashboardUCP

    )
}

fun onboardingUCPStartup(analyticsHandle: AnalyticsHandle, appInfo: AppInfo): OnboardingUCP {
    val locator = IOSServiceLocator(
        NSUserDefaults(suiteName = SETTINGS_KEY),
        analyticsHandle = analyticsHandle,
        appInfo = appInfo
    )
    return locator.onboardingUCP
}

fun dashboardUCPStartup(analyticsHandle: AnalyticsHandle, appInfo: AppInfo): DashboardUCP {
    val locator = IOSServiceLocator(
        NSUserDefaults(suiteName = SETTINGS_KEY),
        analyticsHandle = analyticsHandle,
        appInfo = appInfo
    )
    return locator.dashboardUCP
}
fun sessionServiceStartup(analyticsHandle: AnalyticsHandle, appInfo: AppInfo): UserSessionService {
    val locator = IOSServiceLocator(
        NSUserDefaults(suiteName = SETTINGS_KEY),
        analyticsHandle = analyticsHandle,
        appInfo = appInfo
    )
    return locator.sessionService
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
