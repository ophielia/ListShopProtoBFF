package com.listshop.bff.ucp

import co.touchlab.kmmbridgekickstart.ListShopAnalytics
import com.listshop.bff.data.bff.BFFResult
import com.listshop.bff.data.model.ListShoppingList
import com.listshop.bff.data.model.ShoppingList
import com.listshop.bff.remote.ListShopRemoteApi
import com.listshop.bff.repositories.SessionInfoRepository

class OnboardingUCP internal constructor(
    private val sessionRepo: SessionInfoRepository,
    private val listshopRemoteApi: ListShopRemoteApi,
    private val listShopAnalytics: ListShopAnalytics
) {

    @Throws(Exception::class)
    suspend fun systemGetLaunchScreen(): BFFResult<ListShoppingList> {
        val list = listOf(ShoppingList.create("1", "name"))
        val listOfLists = ListShoppingList(list)
        return BFFResult(listOfLists,null)
    }

}


