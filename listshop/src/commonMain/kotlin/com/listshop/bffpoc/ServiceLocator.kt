package com.listshop.bffpoc

import co.touchlab.kmmbridgekickstart.AppAnalytics
import co.touchlab.kmmbridgekickstart.HttpClientAnalytics
import co.touchlab.kmmbridgekickstart.ListShopAnalytics
import com.listshop.bffpoc.repository.TagUCP

internal interface ServiceLocator {
    val tagUCP: TagUCP
    val appAnalytics: AppAnalytics
    val listShopAnalytics: ListShopAnalytics
    val httpClientAnalytics: HttpClientAnalytics
}
