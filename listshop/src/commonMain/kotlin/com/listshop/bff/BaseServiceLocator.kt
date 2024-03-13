package com.listshop.bff

import app.cash.sqldelight.db.SqlDriver
import co.touchlab.kmmbridgekickstart.AnalyticsHandle
import co.touchlab.kmmbridgekickstart.AppAnalytics
import co.touchlab.kmmbridgekickstart.AppInfo
import co.touchlab.kmmbridgekickstart.HttpClientAnalytics
import co.touchlab.kmmbridgekickstart.ListShopAnalytics
import com.listshop.bff.remote.ListShopRemoteApi
import com.listshop.bff.remote.ListShopRemoteApiImpl
import com.listshop.bff.remote.ShoppingListApi
import com.listshop.bff.remote.ShoppingListApiImpl
import com.listshop.bff.remote.TagApi
import com.listshop.bff.remote.TagApiImpl
import com.listshop.bff.repositories.ListShopDatabase
import com.listshop.bff.repositories.SessionInfoRepository
import com.listshop.bff.repositories.TagRepository
import com.listshop.bff.services.ListService
import com.listshop.bff.services.ListServiceImpl
import com.listshop.bff.services.UserSessionService
import com.listshop.bff.ucp.OnboardingUCP
import com.listshop.bff.ucp.TagUCP
import com.russhwolf.settings.Settings
import io.ktor.client.engine.HttpClientEngine

internal const val SETTINGS_KEY = "KMMBridgeKickStartSettings"
internal const val DB_NAME = "ListshopDb"

internal abstract class BaseServiceLocator(private val analyticsHandle: AnalyticsHandle,
    private val appInfo: AppInfo) :
    ServiceLocator {


    override val tagUCP: TagUCP by lazy {
        TagUCP(
            dataRepo = tagRepository,
            tagApi = tagApi,
            listShopAnalytics = listShopAnalytics
        )
    }

    override val onboardingUCP: OnboardingUCP by lazy {
        OnboardingUCP(
            sessionService = sessionService,
            listService = listService,  // remote repo
            listShopAnalytics = listShopAnalytics
        )
    }

    override val sessionService: UserSessionService by lazy {
        UserSessionService(
            sessionRepo = sessionInfoRepository,
            appInfo = appInfo
        )
    }

    override val appAnalytics: AppAnalytics
        get() = analyticsHandle.appAnalytics


    override val listShopAnalytics: ListShopAnalytics
        get() = analyticsHandle.listShopAnalytics


    override val httpClientAnalytics: HttpClientAnalytics
        get() = analyticsHandle.httpClientAnalytics


    private val listService: ListService by lazy {
        ListServiceImpl(
            remoteApi = shoppingListApi,
            sessionService = sessionService
        )
    }

    private val tagRepository: TagRepository by lazy {
        TagRepository(
            listShopDatabase = listShopDatabase
        )
    }

    private val sessionInfoRepository: SessionInfoRepository by lazy {
        SessionInfoRepository(
            listShopDatabase = listShopDatabase
        )
    }


    private val listShopDatabase: ListShopDatabase by lazy {
        ListShopDatabase(
            sqlDriver = sqlDriver,
            analytics = listShopAnalytics
        )
    }

    private val listShopRemoteApi: ListShopRemoteApi by lazy {
        ListShopRemoteApiImpl(
            engine = clientEngine,
            sessionService = sessionService,
            httpClientAnalytics = httpClientAnalytics,
            listShopAnalytics = listShopAnalytics
        )
    }

    private val tagApi: TagApi by lazy {
        TagApiImpl(
            remoteApi = listShopRemoteApi
        )
    }

    private val shoppingListApi: ShoppingListApi by lazy {
        ShoppingListApiImpl(
            remoteApi = listShopRemoteApi
        )
    }

    protected abstract val sqlDriver: SqlDriver
    protected abstract val settings: Settings
    protected abstract val clientEngine: HttpClientEngine
}
