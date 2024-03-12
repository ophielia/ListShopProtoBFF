package com.listshop.bffpoc

import co.touchlab.kmmbridgekickstart.AppAnalytics
import com.listshop.bff.ucp.OnboardingUCP
import com.listshop.bff.ucp.TagUCP

data class SDKHandle(
    val tagUCP: TagUCP,
    val onboardingUCP: OnboardingUCP,
    val appAnalytics: AppAnalytics
)
