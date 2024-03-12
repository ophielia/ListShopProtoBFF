package com.listshop.bffpoc

import co.touchlab.kmmbridgekickstart.Analytics
import co.touchlab.kmmbridgekickstart.initAnalytics
import co.touchlab.kmmbridgekickstart.initAppInfo

fun startSDK(analytics: Analytics,version: String,
             build: String,
             baseUrl: String): SDKHandle {
    val analyticsHandle = initAnalytics(analytics)
    val appInfo = initAppInfo(version, build, baseUrl)
    return SDKHandle(
        tagUCP = tagUCPStartup(analyticsHandle, appInfo),
        onboardingUCP = onboardingUCPStartup(analyticsHandle, appInfo),
        appAnalytics = analyticsHandle.appAnalytics
    )
}

fun sayHello() = "Hello from Kotlin!"