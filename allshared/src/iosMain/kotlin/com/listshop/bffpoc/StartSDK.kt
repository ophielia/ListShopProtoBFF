package com.listshop.bffpoc

import co.touchlab.kmmbridgekickstart.Analytics
import co.touchlab.kmmbridgekickstart.AppInfo
import co.touchlab.kmmbridgekickstart.initAnalytics

fun startSDK(analytics: Analytics, appInfo : AppInfo): SDKHandle {
    val analyticsHandle = initAnalytics(analytics)
    return SDKHandle(
        tagUCP = tagUCPStartup(analyticsHandle, appInfo),
        onboardingUCP = onboardingUCPStartup(analyticsHandle, appInfo),
        appAnalytics = analyticsHandle.appAnalytics
    )
}

fun sayHello() = "Hello from Kotlin!"