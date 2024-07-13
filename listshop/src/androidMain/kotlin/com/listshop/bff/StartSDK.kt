package com.listshop.bff

import android.content.Context
import co.touchlab.kmmbridgekickstart.Analytics
import co.touchlab.kmmbridgekickstart.AppInfo
import co.touchlab.kmmbridgekickstart.initAnalytics

//MM nfl add build info here
fun startSDK(analytics: Analytics, context: Context,
             appInfo : AppInfo): SDKHandle {
    val analyticsHandle = initAnalytics(analytics)
    val tagUCP = tagUCPStartup(context, analyticsHandle, appInfo)
    val onboardingUCP = onboardingUCPStartup(context, analyticsHandle, appInfo)
    val dashboardUCP = dashboardUCPStartup(context, analyticsHandle, appInfo)
    val sessionService = sessionServiceStartup(context, analyticsHandle, appInfo)
    return SDKHandle(
        appAnalytics = analyticsHandle.appAnalytics,
        tagUCP = tagUCP,
        sessionService = sessionService,
        onboardingUCP = onboardingUCP,
        dashboardUCP = dashboardUCP

    )
}