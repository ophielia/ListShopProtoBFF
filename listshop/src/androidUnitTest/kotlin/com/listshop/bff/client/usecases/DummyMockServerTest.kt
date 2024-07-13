package com.listshop.bff.client.usecases

import co.touchlab.kmmbridgekickstart.Analytics
import co.touchlab.kmmbridgekickstart.AnalyticsHandle
import co.touchlab.kmmbridgekickstart.AppInfo
import co.touchlab.kmmbridgekickstart.initAnalytics
import com.goncalossilva.resources.Resource
import com.listshop.bff.SDKHandle
import com.listshop.bff.TestServiceLocator
import com.listshop.bff.dashboardUCPStartup
import com.listshop.bff.onboardingUCPStartup
import com.listshop.bff.sessionServiceStartup
import com.listshop.bff.tagUCPStartup
import com.listshop.bff.ucp.OnboardingUCP
import com.russhwolf.settings.Settings
import dev.mokkery.mock


import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals


class DummyMockServerTest {

    val responseBodyLogin = """
        {
            "links": [],
            "user": {
                "email": "meg@the-list-shop.com",
                "password": null,
                "roles": [
                    "ROLE_USER",
                    "ROLE_ADMIN"
                ],
                "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtZWdAdGhlLWxpc3Qtc2hvcC5jb20iLCJhdWRpZW5jZSI6Im1vYmlsZSIsImNyZWF0ZWQiOjE3MjA3MjgyNDU4OTJ9._bFBNYJusFDFwaWDoR9fSak7PBEIglqBJQOmNliGbT8vZJ0ZzB0wyf_g5WDV-6eT09laGI5YoxxknmsMbgl13g",
                "creation_date": null,
                "user_name": "meg@the-list-shop.com",
                "user_id": 20
            }
        }
    """.trimIndent()

    val responseBodyList = """
{
  "links": [
    "https://nastyvarmits.fr/shoppinglist"
  ],
  "_embedded": {
    "shoppingListResourceList": [
      {
        "links": [],
        "shopping_list": {
          "list_id": 51067,
          "categories": null,
          "created": "2024-07-07T07:29:49.863+00:00",
          "updated": "2024-07-07T09:46:18.363+00:00",
          "layout_type": null,
          "layout_id": "12",
          "item_count": 4,
          "legend": [],
          "user_id": 20,
          "is_starter_list": false,
          "name": "Shopping List"
        }
      },
      {
        "links": [],
        "shopping_list": {
          "list_id": 50944,
          "categories": null,
          "created": "2023-02-23T15:13:11.171+00:00",
          "updated": "2024-07-07T08:17:06.888+00:00",
          "layout_type": null,
          "layout_id": "12",
          "item_count": 3,
          "legend": [],
          "user_id": 20,
          "is_starter_list": false,
          "name": "Supermarket"
        }
      }
    ]
  }
}
    """.trimIndent()

    val settings = mock<Settings>()

    var useCaseProvider: OnboardingUCP? = null

    @BeforeTest
    fun setUp() {
          val testResource = Resource("src/commonTest/resources/test.json")
         println("RESOURCE IS: "+ testResource.readText())



        val mockWebServer = MockWebServer()
        mockWebServer.start()
        var baseUrl = mockWebServer.url("").toString()
        baseUrl = baseUrl.substring(0,baseUrl.length - 1)
        println("BASE URL IS: " + baseUrl)
        val loginResponse = MockResponse()
            .setBody(responseBodyLogin)
            .setHeader("Content-Type","application/json")
        val listResponse = MockResponse()
            .setBody(responseBodyList)
            .setHeader("Content-Type","application/json")

        val dispatcher: Dispatcher = object : Dispatcher() {
            @Throws(InterruptedException::class)
            override fun dispatch(request: RecordedRequest): MockResponse {
                println("MAKING A REQUEST FOR: " + request.path)
                if (request.path == "/auth") {
                    return loginResponse
                } else if (request.path == "/shoppinglist") {
                    return listResponse
                }
                return MockResponse().setResponseCode(404)
            }
        }
        mockWebServer.dispatcher = dispatcher

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

    @Test
    fun `LoginUseCase - Success with good credentials`() = runTest {
        val userName = "meg@the-list-shop.com"
        val password = "sarrieb1357"

        var result = useCaseProvider?.signIn(userName, password)
        assertEquals("baseUrl", "baseUrl")

    }

}