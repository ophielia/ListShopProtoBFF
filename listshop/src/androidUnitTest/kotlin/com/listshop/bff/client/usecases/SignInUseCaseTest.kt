package com.listshop.bff.client.usecases


import co.touchlab.kmmbridgekickstart.Analytics
import co.touchlab.kmmbridgekickstart.AnalyticsHandle
import co.touchlab.kmmbridgekickstart.AppInfo
import co.touchlab.kmmbridgekickstart.initAnalytics
import com.listshop.bff.SDKHandle
import com.listshop.bff.TestServiceLocator
import com.listshop.bff.dashboardUCPStartup
import com.listshop.bff.onboardingUCPStartup
import com.listshop.bff.sessionServiceStartup
import com.listshop.bff.tagUCPStartup
import com.listshop.bff.test.server.TestDispatcherBuilder
import com.listshop.bff.ucp.OnboardingUCP
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue


class SignInUseCaseTest {

    var useCaseProvider: OnboardingUCP? = null

    val mockWebServer = MockWebServer()

    @BeforeTest
    fun setUp() {
        mockWebServer.start()
        var baseUrl = mockWebServer.url("").toString()
        baseUrl = baseUrl.substring(0,baseUrl.length - 1)

        val newAndBetterDispatcher = TestDispatcherBuilder("signIn")
            .withConfigFile("loginSuccessConfig.json")
            .withConfigFile("getAllShoppingListsConfig.json")
            .build()

        mockWebServer.dispatcher = newAndBetterDispatcher

        val appInfo = AppInfo(
            baseUrl = baseUrl,
            name = "name",
            model = "model",
            os = "os",
            osVersion = "osVersion",
            clientVersion = "clientVersion",
            buildNumber = "buildNumber",
            deviceId = "deviceId"
        )

        val analytics = object : Analytics {
            override fun sendEvent(eventName: String, eventArgs: Map<String, Any>) {
                println("eventName: ${eventName}, eventArgs: ${eventArgs.keys.joinToString(",") { key -> "[$key, ${eventArgs[key]}]" }}")
            }
        }

        val analyticsHandle: AnalyticsHandle = initAnalytics(analytics)
        val locator: TestServiceLocator = TestServiceLocator(analyticsHandle, appInfo)
        val tagUCP = tagUCPStartup(analyticsHandle, appInfo)
        val onboardingUCP = onboardingUCPStartup(analyticsHandle, appInfo)
        val dashboardUCP = dashboardUCPStartup(analyticsHandle, appInfo)
        val sessionService = sessionServiceStartup(analyticsHandle, appInfo)
        val sdkHandle: SDKHandle = SDKHandle(
            appAnalytics = analyticsHandle.appAnalytics,
            tagUCP = tagUCP,
            sessionService = sessionService,
            onboardingUCP = onboardingUCP,
            dashboardUCP = dashboardUCP

        )
        // use driver from locator to insert sql statements, if necessary.

        useCaseProvider = sdkHandle.onboardingUCP
    }

    @AfterTest
    fun shutDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `i can login with correct credentials`(): Unit = runBlocking {
        val userName = "meg@the-list-shop.com"
        val password = "sarrieb1357"

        var result = useCaseProvider?.signIn(userName, password)
        assertNotNull(result)
        assertTrue(result.isSuccess)
    }

}