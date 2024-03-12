package com.listshop.bffpoc

import android.content.Context
import co.touchlab.kmmbridgekickstart.Analytics
import co.touchlab.kmmbridgekickstart.initAnalytics
import co.touchlab.kmmbridgekickstart.initAppInfo

//MM nfl add build info here
fun startSDK(analytics: Analytics, context: Context,
             version: String, build: String, baseUrl: String): SDKHandle {
    val analyticsHandle = initAnalytics(analytics)
    val appInfo = initAppInfo(version, build, baseUrl)
    val tagUCP = tagUCPStartup(context, analyticsHandle)
    val onboardingUCP = onboardingUCPStartup(context, analyticsHandle)
    return SDKHandle(
        appAnalytics = analyticsHandle.appAnalytics,
        tagUCP = tagUCP,
        onboardingUCP = onboardingUCP

    )
}