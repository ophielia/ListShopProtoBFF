package com.listshop.bffpoc

import android.content.Context
import co.touchlab.kmmbridgekickstart.Analytics
import co.touchlab.kmmbridgekickstart.initAnalytics

fun startSDK(analytics: Analytics, context: Context): SDKHandle {
    val analyticsHandle = initAnalytics(analytics)
    val tagUCP = tagUCPStartup(context, analyticsHandle)
    val onboardingUCP = onboardingUCPStartup(context, analyticsHandle)
    return SDKHandle(
        appAnalytics = analyticsHandle.appAnalytics,
        breedAnalytics = analyticsHandle.breedAnalytics,
        tagUCP = tagUCP,
        onboardingUCP = onboardingUCP

    )
}