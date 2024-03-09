package com.listshop.bffpoc

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import co.touchlab.kmmbridgekickstart.AnalyticsHandle
import com.listshop.bffpoc.db.ListshopPOCDb
import com.listshop.bffpoc.repository.TagUCP
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp

internal fun tagUCPStartup(context: Context, analyticsHandle: AnalyticsHandle): TagUCP {
    val locator = AndroidServiceLocator(context, analyticsHandle)
    return locator.tagUCP
}

internal class AndroidServiceLocator(
    context: Context,
    analyticsHandle: AnalyticsHandle
) : BaseServiceLocator(analyticsHandle) {

    override val sqlDriver: SqlDriver by lazy {
        AndroidSqliteDriver(
            schema = ListshopPOCDb.Schema,
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
