package com.listshop.bffpoc

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import co.touchlab.kmmbridgekickstart.AnalyticsHandle
import co.touchlab.kmmbridgekickstart.AppInfo
import com.listshop.bff.BaseServiceLocator
import com.listshop.bff.DB_NAME
import com.listshop.bff.SETTINGS_KEY
import com.listshop.bff.db.ListshopDb
import com.listshop.bff.services.UserSessionService
import com.listshop.bff.ucp.OnboardingUCP
import com.listshop.bff.ucp.TagUCP
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp

internal fun tagUCPStartup(context: Context, analyticsHandle: AnalyticsHandle, appInfo: AppInfo): TagUCP {
    val locator = AndroidServiceLocator(context, analyticsHandle, appInfo)
    return locator.tagUCP
}

internal fun onboardingUCPStartup(context: Context, analyticsHandle: AnalyticsHandle, appInfo: AppInfo): OnboardingUCP {
    val locator = AndroidServiceLocator(context, analyticsHandle, appInfo)
    return locator.onboardingUCP
}
internal fun sessionServiceStartup(context: Context, analyticsHandle: AnalyticsHandle, appInfo: AppInfo): UserSessionService {
    val locator = AndroidServiceLocator(context, analyticsHandle, appInfo)
    return locator.sessionService
}
internal class AndroidServiceLocator(
    context: Context,
    analyticsHandle: AnalyticsHandle,
    appInfo: AppInfo
) : BaseServiceLocator(analyticsHandle, appInfo) {

    override val sqlDriver: SqlDriver by lazy {
        AndroidSqliteDriver(
            schema = ListshopDb.Schema,
            context = context,
            name = DB_NAME
        )
    }

    override val settings: Settings by lazy {
        SharedPreferencesSettings(
            delegate = context.getSharedPreferences(
                SETTINGS_KEY,
                Context.MODE_PRIVATE
            )
        )
    }

    override val clientEngine: HttpClientEngine by lazy { OkHttp.create() }
}
